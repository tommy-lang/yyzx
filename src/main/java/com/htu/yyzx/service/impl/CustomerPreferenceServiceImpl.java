package com.htu.yyzx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.ErrorCode;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.CustomerMapper;
import com.htu.yyzx.mapper.CustomerPreferenceMapper;
import com.htu.yyzx.model.dto.customer.CustomerPreferenceAddRequest;
import com.htu.yyzx.model.dto.customer.CustomerPreferenceQueryRequest;
import com.htu.yyzx.model.dto.customer.CustomerPreferenceUpdateRequest;
import com.htu.yyzx.model.entity.Customer;
import com.htu.yyzx.model.entity.CustomerPreference;
import com.htu.yyzx.model.vo.CustomerPreferenceVO;
import com.htu.yyzx.service.CustomerPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerPreferenceServiceImpl extends ServiceImpl<CustomerPreferenceMapper, CustomerPreference>
        implements CustomerPreferenceService {

    private final CustomerPreferenceMapper customerPreferenceMapper;
    private final CustomerMapper customerMapper;

    /**
     * 查询
     *
     * @param customerPreferenceQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<CustomerPreferenceVO>> queryCustomerPreference(CustomerPreferenceQueryRequest customerPreferenceQueryRequest) {
        // List<CustomerPreference> byCustomerPreferencePage = customerPreferenceMapper.findByCustomerPreferencePage(customerPreferenceQueryRequest.getCustomerName());
        String customerName = customerPreferenceQueryRequest.getCustomerName();
        int current = customerPreferenceQueryRequest.getCurrent();
        int pageSize = customerPreferenceQueryRequest.getPageSize();
        List<Integer> list = customerMapper.selectList(new LambdaQueryWrapper<>(Customer.class).like(Customer::getCustomerName, customerName))
                .stream()
                .map(Customer::getId)
                .toList();
        Page<CustomerPreference> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<CustomerPreference>().in(CustomerPreference::getCustomerId, list));
        Page<CustomerPreferenceVO> pageVo = new Page<>(current, pageSize, page.getTotal());
        pageVo.setRecords(page.getRecords().stream()
                .map(this::getCustomerPreferenceVO).toList());

        return R.success(pageVo);
    }

    /**
     * 删除膳食偏好
     *
     * @param deleteRequestBody
     * @return
     */
    @Override
    public BaseResponse<String> deleteCustomerPreference(DeleteRequestBody deleteRequestBody) {
        List<Long> ids = deleteRequestBody.getIds();
        if (ids.size() == 1) {
            removeById(ids.get(0));
        } else {
            customerPreferenceMapper.deleteBatchIds(ids);
        }
        return R.success(null, "删除成功");
    }

    /**
     * 添加膳食
     *
     * @param customerPreferenceAddRequest
     * @return
     */
    @Override
    @Transactional
    public BaseResponse<Page<CustomerPreferenceVO>> addCustomerPreference(CustomerPreferenceAddRequest customerPreferenceAddRequest) {

        CustomerPreference customerPreference = BeanUtil.copyProperties(customerPreferenceAddRequest, CustomerPreference.class);
        int insert = baseMapper.insert(customerPreference);
        if (insert != 0) {
            return R.success(null, "添加成功");
        }
        return R.error(ErrorCode.OPERATION_ERROR, "添加失败");
    }

    /**
     * 更新膳食管理列表
     * @param customerPreferenceUpdateRequest
     * @return
     */
    @Override
    public BaseResponse<Page<CustomerPreferenceVO>> updateCustomerPreference(CustomerPreferenceUpdateRequest customerPreferenceUpdateRequest) {
        CustomerPreference customerPreference = BeanUtil.copyProperties(customerPreferenceUpdateRequest, CustomerPreference.class);
        int update = baseMapper.updateById(customerPreference);
        if (update != 0) {
            return R.success(null, "更新成功");
        }
        return R.error(ErrorCode.OPERATION_ERROR, "更新失败");
    }

    private CustomerPreferenceVO getCustomerPreferenceVO(CustomerPreference customerPreference) {
        CustomerPreferenceVO customerPreferenceVO = BeanUtil.copyProperties(customerPreference, CustomerPreferenceVO.class);
        Integer customerId = customerPreference.getCustomerId();
        Optional.ofNullable(customerMapper.selectOne(new LambdaQueryWrapper<>(Customer.class).eq(Customer::getId, customerId)))
                .ifPresent(customer -> {
                    customerPreferenceVO.setCustomerName(customer.getCustomerName());
                    customerPreferenceVO.setCustomerAge(customer.getCustomerAge());
                    customerPreferenceVO.setCustomerSex(customer.getCustomerSex());
                });
        return customerPreferenceVO;
    }
}




