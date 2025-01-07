package com.htu.yyzx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.BedDetailsMapper;
import com.htu.yyzx.mapper.BedMapper;
import com.htu.yyzx.mapper.CustomerMapper;
import com.htu.yyzx.mapper.OutwardMapper;
import com.htu.yyzx.model.dto.customer.CheckOutRevokedRequest;
import com.htu.yyzx.model.dto.customer.OutwardApprovalRequest;
import com.htu.yyzx.model.dto.customer.OutwardQueryRequest;
import com.htu.yyzx.model.dto.customer.OutwardRemarkRequest;
import com.htu.yyzx.model.entity.Bed;
import com.htu.yyzx.model.entity.BedDetails;
import com.htu.yyzx.model.entity.Customer;
import com.htu.yyzx.model.entity.Outward;
import com.htu.yyzx.model.enums.AuditStatus;
import com.htu.yyzx.model.enums.BedStatus;
import com.htu.yyzx.model.vo.OutwardVO;
import com.htu.yyzx.service.OutwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutwardServiceImpl extends ServiceImpl<OutwardMapper, Outward>
        implements OutwardService {

    private final CustomerMapper customerMapper;
    private final BedDetailsMapper bedDetailsMapper;
    private final BedMapper bedMapper;

    /**
     * 查询指定名字的客户的所有外出记录
     * @param outwardQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<OutwardVO>> queryOutwardDetails(OutwardQueryRequest outwardQueryRequest) {
        String customerName = outwardQueryRequest.getCustomerName();
        int current = outwardQueryRequest.getCurrent();
        int pageSize = outwardQueryRequest.getPageSize();
        String sortField = outwardQueryRequest.getSortField();
        String sortOrder = outwardQueryRequest.getSortOrder();
        Integer id = customerMapper.selectOne(new LambdaQueryWrapper<Customer>()
                .select(Customer::getId)
                .eq(Customer::getCustomerName, customerName)
        ).getId(); // 根据客户姓名查询客户id
        Page<Outward> page = page(new Page<>(current, pageSize),
                new LambdaQueryWrapper<Outward>()
                        .eq(Outward::getCustomerId, id));
        Page<OutwardVO> outwardPage = new Page<>(current, pageSize, page.getTotal());
        List<OutwardVO> list1 = page.getRecords().stream().map(outward -> {
            OutwardVO outwardVO = BeanUtil.copyProperties(outward, OutwardVO.class);
            outwardVO.setCustomerName(customerName);
            return outwardVO;
        }).toList();
        outwardPage.setRecords(list1);
        return R.success(outwardPage);
    }

    /**
     * 撤销外出申请
     *
     * @param revokedOutwardRequest
     * @return
     */
    @Override
    public BaseResponse<String> revokedOutward(CheckOutRevokedRequest revokedOutwardRequest) {
        baseMapper.deleteById(revokedOutwardRequest.getId()); // 根据外出记录id删除外出记录
        return R.success("外出撤销成功");

    }

    /**
     * 审批外出
     *
     * @param outwardApprovalRequest
     * @return
     */
    @Override
    public BaseResponse<String> approvalOutward(OutwardApprovalRequest outwardApprovalRequest) {
        Outward entity = BeanUtil.copyProperties(outwardApprovalRequest, Outward.class);
        baseMapper.updateById(entity); // 根据外出记录id更新外出记录
        Integer customerId = entity.getCustomerId();
        BedDetails bedDetails = bedDetailsMapper.selectOne(new LambdaQueryWrapper<BedDetails>()
                .eq(BedDetails::getCustomerId, customerId));
        if (bedDetails != null) {
            Integer bedId = bedDetails.getBedId();
            // 将床位状态改为空闲 or 占用
            // TODO 逻辑上还有漏洞，需要判断外出审核状态
            bedMapper.update(new LambdaUpdateWrapper<Bed>()
                    .set(Bed::getStatus,
                            entity.getAuditStatus() == AuditStatus.DIE.getCode() ?
                                    BedStatus.VACANCY.getCode() : BedStatus.OCCUPATION.getCode()
                    ).eq(Bed::getId, bedId));
            bedDetailsMapper.deleteById(bedDetails.getId()); // 根据客户id删除床位详情
        }

        return R.success("外出审核成功");
    }

    /**
     * 外出登记
     *
     * @param outwardRemarkRequest
     * @return
     */
    @Override
    public BaseResponse<String> remarkOutward(OutwardRemarkRequest outwardRemarkRequest) {
        String customerName = outwardRemarkRequest.getCustomerName();
        Outward outward = BeanUtil.copyProperties(outwardRemarkRequest, Outward.class);
        outward.setAuditStatus(AuditStatus.NORMAL.getCode());
        Optional.ofNullable(customerMapper.selectOne(new LambdaQueryWrapper<Customer>()
                        .eq(Customer::getCustomerName, customerName)))
                .ifPresent(customer -> {
                    outward.setCustomerId(customer.getId());
                });
        baseMapper.insert(outward); // 插入退住记录
        return R.success("");
    }
}




