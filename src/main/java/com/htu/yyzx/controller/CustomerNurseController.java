
package com.htu.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.model.dto.CustomerNurse.CustomerNurseQueryRequest;
import com.htu.yyzx.model.dto.CustomerNurse.CustomerNurseUpdateRequest;
import com.htu.yyzx.model.vo.CustomerNurseVO;
import com.htu.yyzx.service.CustomerNurseItemService;
import com.htu.yyzx.service.CustomerService;
import com.htu.yyzx.service.NurseContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurse")
@RequiredArgsConstructor
public class CustomerNurseController {

    private final CustomerService customerService;
    private final CustomerNurseItemService customerNurseItemService;
    private final NurseContentService nurseContentService;

    /**
     * 分页查询客户-房间-护理等级
     *
     * @param customerNurseQueryRequest
     * @return
     */
    @PostMapping("/queryCustomerNurse")
    public BaseResponse<Page<CustomerNurseVO>> queryCustomerNurse(
            @RequestBody CustomerNurseQueryRequest customerNurseQueryRequest
    ) {
        return customerService.queryCustomerNurse(customerNurseQueryRequest);
    }

    /**
     * 设置用户护理登记
     *
     * @param customerNurseUpdateRequest
     * @return
     */
    @PostMapping("/updateCustomerNurse")
    public BaseResponse<Page<CustomerNurseVO>> updateCustomerNurse(
            @RequestBody @Validated CustomerNurseUpdateRequest customerNurseUpdateRequest
    ) {
        return customerService.updateCustomerNurse(customerNurseUpdateRequest);
    }

}
