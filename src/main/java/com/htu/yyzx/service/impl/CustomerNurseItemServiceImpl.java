package com.htu.yyzx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.CustomerMapper;
import com.htu.yyzx.mapper.CustomerNurseItemMapper;
import com.htu.yyzx.mapper.NurseContentMapper;
import com.htu.yyzx.mapper.NurseLevelMapper;
import com.htu.yyzx.model.dto.healthcare.BoughtOrNotNurseServiceQueryRequest;
import com.htu.yyzx.model.dto.healthcare.CustomerNurseAddRequest;
import com.htu.yyzx.model.dto.healthcare.RenewalNurseServiceUpdateRequest;
import com.htu.yyzx.model.entity.Customer;
import com.htu.yyzx.model.entity.CustomerNurseItem;
import com.htu.yyzx.model.entity.NurseContent;
import com.htu.yyzx.model.entity.NurseLevel;
import com.htu.yyzx.model.enums.ExecutionCycle;
import com.htu.yyzx.model.vo.BoughtNurseServiceVO;
import com.htu.yyzx.service.CustomerNurseItemService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerNurseItemServiceImpl extends ServiceImpl<CustomerNurseItemMapper, CustomerNurseItem>
        implements CustomerNurseItemService {

    private final NurseContentMapper nurseContentMapper;
    private final NurseLevelMapper nurseLevelMapper;
    private final CustomerMapper customerMapper;

    /**
     * 查询指定客户已购买的服务
     *
     * @param boughtOrNotNurseServiceQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<BoughtNurseServiceVO>> queryBoughtNurseService(BoughtOrNotNurseServiceQueryRequest boughtOrNotNurseServiceQueryRequest) {

        Integer customerId = boughtOrNotNurseServiceQueryRequest.getCustomerId();
        int current = boughtOrNotNurseServiceQueryRequest.getCurrent();
        int pageSize = boughtOrNotNurseServiceQueryRequest.getPageSize();
        String sortField = boughtOrNotNurseServiceQueryRequest.getSortField();
        String sortOrder = boughtOrNotNurseServiceQueryRequest.getSortOrder();

        // 获取已购买服务分页数据
        Page<CustomerNurseItem> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<CustomerNurseItem>()
                .eq(CustomerNurseItem::getCustomerId, customerId));

        Page<BoughtNurseServiceVO> boughtNurseServiceVOPage = new Page<>(current, pageSize, page.getTotal());
        boughtNurseServiceVOPage.setRecords(page.getRecords().stream().map(this::getBoughtNurseServiceVO).toList());
        return R.success(boughtNurseServiceVOPage, "查询成功");
    }

    /**
     * 续费指定客户已购买的服务
     *
     * @param renewalNurseServiceUpdateRequest
     * @return
     */
    @Override
    public BaseResponse<Page<BoughtNurseServiceVO>> renewalNurseService(RenewalNurseServiceUpdateRequest renewalNurseServiceUpdateRequest) {
        Integer nurseNumber = renewalNurseServiceUpdateRequest.getNurseNumber();
        Integer addedNumber = renewalNurseServiceUpdateRequest.getAddedNumber();
        CustomerNurseItem customerNurseItem = BeanUtil.copyProperties(renewalNurseServiceUpdateRequest, CustomerNurseItem.class);
        // 添加续费数量
        customerNurseItem.setNurseNumber(nurseNumber + addedNumber);
        // 更新续费时间
        updateById(customerNurseItem);
        return R.success(null, "续费成功");
    }

    /**
     * 删除指定客户已购买的服务
     *
     * @param deleteRequestBody
     * @return
     */
    @Override
    public BaseResponse<Page<BoughtNurseServiceVO>> deleteNurseService(DeleteRequestBody deleteRequestBody) {
        baseMapper.deleteBatchIds(deleteRequestBody.getIds());
        // TODO 是否对 nurse_record 添加
        return R.success(null, "移除成功");
    }

    /**
     * 添加指定客户购买的服务
     *
     * @param customerNurseAddRequest
     * @return
     */
    @Override
    public BaseResponse<String> addNurseService(List<CustomerNurseAddRequest> customerNurseAddRequest) {
        customerNurseAddRequest.forEach(Item -> {
            Integer customerId = Item.getCustomerId();
            Integer itemId = Item.getItemId();
            Customer customer = customerMapper.selectOne(new LambdaQueryWrapper<Customer>()
                    .eq(Customer::getId, customerId));
            NurseContent nurseContent = nurseContentMapper.selectOne(new LambdaQueryWrapper<NurseContent>().eq(NurseContent::getId, itemId));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int days = 0;
            if (ExecutionCycle.DAY.getMessage().equals(nurseContent.getExecutionCycle())) {
                days += 1;
            } else if (ExecutionCycle.WEEK.getMessage().equals(nurseContent.getExecutionCycle())) {
                days += 7;
            } else if (ExecutionCycle.MONTH.getMessage().equals(nurseContent.getExecutionCycle())) {
                days += 30;
            }
            days *= Integer.parseInt(nurseContent.getExecutionTimes());

            Date maturityTime = new Date(new Date().getTime() + days * 24 * 60 * 60 * 1000L);
            CustomerNurseItem build = CustomerNurseItem.builder()
                    .itemId(itemId)
                    .customerId(customerId)
                    .levelId(customer.getLevelId())
                    .nurseNumber(Integer.valueOf(nurseContent.getExecutionTimes()))
                    .buyTime(new Date())
                    .isDeleted(0)
                    .maturityTime(maturityTime)
                    .build();
            baseMapper.insert(build);
        });
        return R.success(null, "添加成功");
    }

    /**
     * CustomerNurseItem 类 -> BoughtNurseServiceVO 类
     *
     * @param customerNurseItem
     * @return
     */
    @NotNull
    private BoughtNurseServiceVO getBoughtNurseServiceVO(CustomerNurseItem customerNurseItem) {
        BoughtNurseServiceVO boughtNurseServiceVO = BeanUtil.copyProperties(customerNurseItem, BoughtNurseServiceVO.class);
        String levelName = boughtNurseServiceVO.getLevelName();
        NurseContent nurseContent = nurseContentMapper.selectOne(new LambdaQueryWrapper<NurseContent>()
                .eq(NurseContent::getId, customerNurseItem.getItemId()));
        boughtNurseServiceVO.setSerialNumber(nurseContent.getSerialNumber());
        boughtNurseServiceVO.setExecutionCycle(nurseContent.getExecutionCycle());
        boughtNurseServiceVO.setItemName(nurseContent.getNursingName());
        boughtNurseServiceVO.setPrice(nurseContent.getServicePrice());
        // 获取护理等级名称
        Optional.ofNullable(customerNurseItem.getLevelId())
                .ifPresent(levelId -> {
                    NurseLevel nurseLevel = nurseLevelMapper.selectOne(new LambdaQueryWrapper<NurseLevel>()
                            .eq(NurseLevel::getId, levelId));
                    boughtNurseServiceVO.setLevelName(nurseLevel.getLevelName());
                });

        return boughtNurseServiceVO;
    }
}




