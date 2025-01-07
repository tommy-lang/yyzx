package com.htu.yyzx.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.*;
import com.htu.yyzx.model.dto.customer.*;
import com.htu.yyzx.model.entity.*;
import com.htu.yyzx.model.enums.AuditStatus;
import com.htu.yyzx.model.enums.BedStatus;
import com.htu.yyzx.model.vo.CheckOutVO;
import com.htu.yyzx.service.BackDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BackDownServiceImpl extends ServiceImpl<BackDownMapper, BackDown>
        implements BackDownService {

    private final BedMapper bedMapper;
    private final NurseLevelMapper nurseLevelMapper;
    private final CustomerMapper customerMapper;
    private final BedDetailsMapper bedDetailsMapper;
    private final UserMapper userMapper;

    /**
     * 查询退住登录详情
     *
     * @param checkOutQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<CheckOutVO>> queryCheckOutDetails(CheckOutQueryRequest checkOutQueryRequest) {
        String customerName = checkOutQueryRequest.getCustomerName();
        int current = checkOutQueryRequest.getCurrent();
        int pageSize = checkOutQueryRequest.getPageSize();
        String sortField = checkOutQueryRequest.getSortField();
        String sortOrder = checkOutQueryRequest.getSortOrder();
        Integer id = customerMapper.selectOne(new LambdaQueryWrapper<Customer>()
                .select(Customer::getId)
                .eq(Customer::getCustomerName, customerName)
        ).getId(); // 根据客户姓名查询客户id

        Page<BackDown> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<BackDown>()
                .eq(BackDown::getCustomerId, id));

        Page<CheckOutVO> backDownPage = new Page<>(current, pageSize, page.getTotal());
        List<CheckOutVO> list1 = page.getRecords().stream().map(backDown -> {
            CheckOutVO checkOutVO = BeanUtil.copyProperties(backDown, CheckOutVO.class);
            checkOutVO.setCustomerName(customerName);
            return checkOutVO;
        }).toList();
        backDownPage.setRecords(list1);
        return R.success(backDownPage);
    }

    @Override
    public BaseResponse<String> revokedCheckOut(CheckOutRevokedRequest checkOutRevokedRequest) {
        baseMapper.deleteById(checkOutRevokedRequest.getId()); // 根据退住记录id删除退住记录
        return R.success("退住撤销成功");
    }

    /**
     * 审批
     *
     * @param checkOutApprovalRequest
     * @return
     */
    @Override
    public BaseResponse<String> approvalCheckOut(CheckOutApprovalRequest checkOutApprovalRequest) {

        BackDown entity = BeanUtil.copyProperties(checkOutApprovalRequest, BackDown.class);

        Integer loginId = StpUtil.getLoginIdAsInt();// 获取当前登录用户id
        Optional.ofNullable(userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .eq(User::getId, loginId)))
                .ifPresent(user -> {
                    entity.setAuditPerson(user.getNickname());
                }); // 判断当前登录用户是否为员工

        baseMapper.updateById(entity); // 根据退住记录id更新退住记录
        Integer customerId = entity.getCustomerId();

        BedDetails bedDetails = bedDetailsMapper.selectOne(new LambdaQueryWrapper<BedDetails>()
                .eq(BedDetails::getCustomerId, customerId));
        if (bedDetails != null) {
            Integer bedId = bedDetails.getBedId();
            // 将床位状态改为空闲 or 占用
            // TODO 逻辑上还有漏洞，需要判断退住审核状态
            bedMapper.update(new LambdaUpdateWrapper<Bed>()
                    .set(Bed::getStatus,
                            entity.getAuditStatus() == AuditStatus.DIE.getCode() ?
                                    BedStatus.VACANCY.getCode() : BedStatus.OCCUPATION.getCode()
                    ).eq(Bed::getId, bedId));
            bedDetailsMapper.deleteById(bedDetails.getId()); // 根据客户id删除床位详情
        }

        return R.success("退住审核成功");
    }

    /**
     * 退住登记
     *
     * @param checkOutRemarkRequest
     * @return
     */
    @Override
    public BaseResponse<String> remarkCheckOut(CheckOutRemarkRequest checkOutRemarkRequest) {
        String customerName = checkOutRemarkRequest.getCustomerName();
        BackDown backDown = BeanUtil.copyProperties(checkOutRemarkRequest, BackDown.class);
        backDown.setAuditStatus(AuditStatus.NORMAL.getCode());
        Optional.ofNullable(customerMapper.selectOne(new LambdaQueryWrapper<Customer>()
                        .eq(Customer::getCustomerName, customerName)))
                .ifPresent(customer -> {
                    backDown.setCustomerId(customer.getId());
                });
        baseMapper.insert(backDown); // 插入退住记录
        return null;
    }

    @Override
    public BaseResponse<Page<CheckOutVO>> queryOutwardDetails(OutwardQueryRequest outwardQueryRequest) {
        return null;
    }
}




