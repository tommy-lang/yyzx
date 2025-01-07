package com.htu.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.PageRequest;
import com.htu.yyzx.model.dto.HealthSereviceS.HealthAdminAddRequest;
import com.htu.yyzx.model.dto.HealthSereviceS.HealthServiceSQueryRequets;
import com.htu.yyzx.model.dto.user.UserQueryRequest;
import com.htu.yyzx.model.entity.User;
import com.htu.yyzx.model.vo.CustomerDetailsVO;
import com.htu.yyzx.service.*;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthServiceSController {

    private final CustomerService customerService;
    private final UserService userService;
    private final NurseContentService nurseContentService;
    private final BackDownService backDownService;
    private final OutwardService outwardService;

    /**
     * 分页查询健康管家
     *
     * @param healthAdminQueryRequest
     * @return
     */
    @PostMapping("/queryHealthAdmin")
    public BaseResponse<Page<User>> queryHealthAdmin(
            @RequestBody UserQueryRequest healthAdminQueryRequest
    ) {
        return userService.queryHealthAdmin(healthAdminQueryRequest);
    }

    /**
     * 分页查询指定用户服务的客户
     *
     * @param healthServiceSQueryRequets
     * @return
     */
    @PostMapping("/queryCustomerByHealthAdmin")
    public BaseResponse<Page<CustomerDetailsVO>> queryCustomerByHealthAdmin(
            @RequestBody HealthServiceSQueryRequets healthServiceSQueryRequets
    ) {
        return customerService.queryCustomerByHealthAdmin(healthServiceSQueryRequets);
    }

    /**
     * 分页查询无健康管家的客户
     *
     * @return
     */
    @PostMapping("/queryCustomerByNoHealthAdmin")
    public BaseResponse<Page<CustomerDetailsVO>> queryCustomerByNoHealthAdmin(
            @RequestBody PageRequest pageRequest
    ) {
        return customerService.queryCustomerByNoHealthAdmin(pageRequest);
    }

    /**
     * 删除
     *
     * @param deleteRequestBody
     * @return
     */
    @PostMapping("/deleteServiceS")
    public BaseResponse<String> deleteServiceS(
            @RequestBody @Validated DeleteRequestBody deleteRequestBody
    ) {
        return customerService.deleteServiceS(deleteRequestBody);
    }

    /**
     * 给用户添加健康管家（单项 or 批量）
     *
     * @param healthAdminAddRequest
     * @return
     */
    @PostMapping("/addHealthAdmin")
    public BaseResponse<String> addHealthAdmin(
            @RequestBody @Validated @Size(min = 1, message = "传入数据错误") List<HealthAdminAddRequest> healthAdminAddRequest
    ) {
        return customerService.addHealthAdmin(healthAdminAddRequest);
    }
}
