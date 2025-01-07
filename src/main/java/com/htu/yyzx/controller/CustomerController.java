package com.htu.yyzx.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.R;

import com.htu.yyzx.model.dto.customer.*;
import com.htu.yyzx.model.entity.Customer;

import com.htu.yyzx.model.vo.*;
import com.htu.yyzx.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;
    private final NurseLevelService nurseLevelService;
    private final BackDownService backDownService;
    private final OutwardService outwardService;
    private final CustomerPreferenceService customerPreferenceService;

    /**
     * 分页查询入住登记
     *
     * @param checkInQueryRequest
     * @return
     */
    @PostMapping("/queryCheckIn")
    public BaseResponse<Page<CheckInVO>> queryCheckIn(
            @RequestBody CheckInQueryRequest checkInQueryRequest
    ) {
        return customerService.queryCheckIn(checkInQueryRequest);
    }

    /**
     * 添加入住登记
     *
     * @param checkInAddRequest
     * @return
     */
    @PostMapping("/addCheckIn")
    public BaseResponse<String> addCheckIn(
            @RequestBody CheckInAddRequest checkInAddRequest
    ) {
        return customerService.addCheckIn(checkInAddRequest);
    }

    /**
     * 更详细信息
     *
     * @param checkInUpdateRequest
     * @return
     */
    @PostMapping("/updateCheckIn")
    public BaseResponse<Boolean> updateCheckIn(
            @RequestBody CheckInUpdateRequest checkInUpdateRequest
    ) {
        Customer customer = BeanUtil.copyProperties(checkInUpdateRequest, Customer.class);
        System.out.println(checkInUpdateRequest);
        return R.success(customerService.updateById(customer));
    }

    /**
     * 删除客户信息
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/deleteCheckIn")
    public BaseResponse<String> deleteCheckIn(
            @RequestBody DeleteRequestBody deleteRequest
    ) {
        return customerService.deleteCheckIn(deleteRequest);
    }

    /**
     * 模糊获取客户姓名列表
     *
     * @param checkOutQueryRequest
     * @return
     */
    @PostMapping("/queryCustomerName")
    public BaseResponse<List<String>> queryCustomerName(
            @RequestBody CheckOutQueryRequest checkOutQueryRequest
    ) {
        return customerService.queryCustomerName(checkOutQueryRequest);
    }

    /**
     * 获取全部管家用户
     *
     * @return
     */
    @GetMapping("/queryAllUser")
    public BaseResponse<List<String>> queryAllUser() {
        return userService.queryAllUser();
    }

    /**
     * 获取全部护理等级
     *
     * @return
     */
    @GetMapping("/queryAllLevel")
    public BaseResponse<List<String>> queryAllLevel() {
        return nurseLevelService.queryAllLevel();
    }

    /**
     * 分页查询客户详情
     *
     * @param customerDetailsQueryRequest
     * @return
     */
    @PostMapping("/queryCustomerDetails")
    public BaseResponse<Page<CustomerDetailsVO>> queryCustomerDetails(
            @RequestBody CustomerDetailsQueryRequest customerDetailsQueryRequest
    ) {
        return customerService.queryCustomerDetails(customerDetailsQueryRequest);
    }

    /**
     * 分页查询退住登记详情
     *
     * @param checkOutQueryRequest
     * @return
     */
    @PostMapping("/queryCheckOutDetails")
    public BaseResponse<Page<CheckOutVO>> queryCheckOutDetails(
            @RequestBody CheckOutQueryRequest checkOutQueryRequest
    ) {
        return backDownService.queryCheckOutDetails(checkOutQueryRequest);
    }

    /**
     * 撤销申请
     *
     * @param checkOutRevokedRequest
     * @return
     */
    @PostMapping("/revokedCheckOut")
    public BaseResponse<String> revokedCheckOut(
            @RequestBody CheckOutRevokedRequest checkOutRevokedRequest
    ) {
        return backDownService.revokedCheckOut(checkOutRevokedRequest);
    }

    /**
     * 审批退住
     *
     * @param checkOutApprovalRequest
     * @return
     */
    @PostMapping("/approvalCheckOut")
    public BaseResponse<String> approvalCheckOut(
            @RequestBody CheckOutApprovalRequest checkOutApprovalRequest
    ) {
        return backDownService.approvalCheckOut(checkOutApprovalRequest);
    }

    /**
     * 退住登记
     *
     * @param checkOutRemarkRequest
     * @return
     */
    @PostMapping("/remarkCheckOut")
    public BaseResponse<String> remarkCheckOut(
            @RequestBody CheckOutRemarkRequest checkOutRemarkRequest
    ) {
        return backDownService.remarkCheckOut(checkOutRemarkRequest);
    }

    /**
     * 分页查询客户详情-外出
     *
     * @param customerDetailsQueryRequest
     * @return
     */
    @PostMapping("/queryOutwardCustomer")
    public BaseResponse<Page<CustomerDetailsVO>> queryOutwardCustomer(
            @RequestBody CustomerDetailsQueryRequest customerDetailsQueryRequest
    ) {
        return customerService.queryOutwardCustomer(customerDetailsQueryRequest);
    }

    /**
     * 分页查询外出登记详情
     *
     * @param outwardQueryRequest
     * @return
     */
    @PostMapping("/queryOutwardDetails")
    public BaseResponse<Page<OutwardVO>> queryOutwardDetails(
            @RequestBody OutwardQueryRequest outwardQueryRequest
    ) {
        return outwardService.queryOutwardDetails(outwardQueryRequest);
    }


    /**
     * 外出撤销申请
     *
     * @param revokedOutwardRequest
     * @return
     */
    @PostMapping("/revokedOutward")
    public BaseResponse<String> revokedOutward(
            @RequestBody CheckOutRevokedRequest revokedOutwardRequest
    ) {
        return outwardService.revokedOutward(revokedOutwardRequest);
    }

    /**
     * 审批外出
     *
     * @param outwardApprovalRequest
     * @return
     */
    @PostMapping("/approvalOutward")
    public BaseResponse<String> approvalOutward(
            @RequestBody OutwardApprovalRequest outwardApprovalRequest
    ) {
        return outwardService.approvalOutward(outwardApprovalRequest);
    }

    /**
     * 外出登记
     *
     * @param outwardRemarkRequest
     * @return
     */
    @PostMapping("/remarkOutward")
    public BaseResponse<String> remarkOutward(
            @RequestBody OutwardRemarkRequest outwardRemarkRequest
    ) {
        return outwardService.remarkOutward(outwardRemarkRequest);
    }

    /**
     * 查询膳食列表
     *
     * @param customerPreferenceQueryRequest
     * @return
     */
    @PostMapping("/queryCustomerPreference")
    public BaseResponse<Page<CustomerPreferenceVO>> queryCustomerPreference(
            @RequestBody CustomerPreferenceQueryRequest customerPreferenceQueryRequest
    ) {
        return customerPreferenceService.queryCustomerPreference(customerPreferenceQueryRequest);
    }

    /**
     * 获取无膳食偏好的顾客
     *
     * @param customerName
     * @return
     */
    @GetMapping("/queryCustomerOfNoPreference")
    public BaseResponse<NoPreferenceVO> queryCustomerOfNoPreference(
            @RequestParam String customerName
    ) {
        return customerService.queryCustomerOfNoPreference(customerName);
    }


    /**
     * 添加膳食列表
     *
     * @param customerPreferenceAddRequest
     * @return
     */
    @PostMapping("/addCustomerPreference")
    public BaseResponse<Page<CustomerPreferenceVO>> addCustomerPreference(
            @RequestBody CustomerPreferenceAddRequest customerPreferenceAddRequest
    ) {
        return customerPreferenceService.addCustomerPreference(customerPreferenceAddRequest);
    }

    /**
     * 更新膳食管理列表
     *
     * @param customerPreferenceUpdateRequest
     * @return
     */
    @PostMapping("/updateCustomerPreference")
    public BaseResponse<Page<CustomerPreferenceVO>> updateCustomerPreference(
            @RequestBody CustomerPreferenceUpdateRequest customerPreferenceUpdateRequest
    ) {
        return customerPreferenceService.updateCustomerPreference(customerPreferenceUpdateRequest);
    }


    /**
     * 删除膳食列表
     *
     * @param deleteRequestBody
     * @return
     */
    @PostMapping("/deleteCustomerPreference")
    public BaseResponse<String> deleteCustomerPreference(
            @RequestBody DeleteRequestBody deleteRequestBody
    ) {
        return customerPreferenceService.deleteCustomerPreference(deleteRequestBody);
    }

}
