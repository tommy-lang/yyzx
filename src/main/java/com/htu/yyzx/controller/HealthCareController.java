package com.htu.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.CustomerNurse.CustomerNurseQueryRequest;
import com.htu.yyzx.model.dto.healthcare.BoughtOrNotNurseServiceQueryRequest;
import com.htu.yyzx.model.dto.healthcare.CustomerNurseAddRequest;
import com.htu.yyzx.model.dto.healthcare.RenewalNurseServiceUpdateRequest;
import com.htu.yyzx.model.entity.NurseContent;
import com.htu.yyzx.model.vo.BoughtNurseServiceVO;
import com.htu.yyzx.model.vo.CustomerDetailsVO;
import com.htu.yyzx.service.CustomerNurseItemService;
import com.htu.yyzx.service.CustomerService;
import com.htu.yyzx.service.NurseContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/health")
public class HealthCareController {

    private final CustomerService customerService;
    private final CustomerNurseItemService customerNurseItemService;
    private final NurseContentService nurseContentService;

    /**
     * 分页查询客户信息
     *
     * @param customerNurseQueryRequest
     * @return
     */
    @PostMapping("/queryCustomerList")
    public BaseResponse<Page<CustomerDetailsVO>> queryCustomerList(
            @RequestBody CustomerNurseQueryRequest customerNurseQueryRequest
    ) {
        return customerService.queryCustomerList(customerNurseQueryRequest);
    }

    /**
     * 分页查询已购护理服务
     *
     * @param boughtOrNotNurseServiceQueryRequest
     * @return
     */
    @PostMapping("/queryBoughtNurseService")
    public BaseResponse<Page<BoughtNurseServiceVO>> queryBoughtNurseService(
            @RequestBody BoughtOrNotNurseServiceQueryRequest boughtOrNotNurseServiceQueryRequest
    ) {
        return customerNurseItemService.queryBoughtNurseService(boughtOrNotNurseServiceQueryRequest);
    }

    /**
     * 分页查询未购护理服务 by customerId
     *
     * @param boughtOrNotNurseServiceQueryRequest
     * @return
     */
    @PostMapping("/queryNotBoughtNurseService")
    public BaseResponse<Page<NurseContent>> queryNotBoughtNurseService(
            @RequestBody BoughtOrNotNurseServiceQueryRequest boughtOrNotNurseServiceQueryRequest
    ) {
        return nurseContentService.queryNotBoughtNurseService(boughtOrNotNurseServiceQueryRequest);
    }

    /**
     * 护理服务续费
     *
     * @param renewalNurseServiceUpdateRequest
     * @return
     */
    @PostMapping("/renewalNurseService")
    public BaseResponse<Page<BoughtNurseServiceVO>> renewalNurseService(
            @RequestBody RenewalNurseServiceUpdateRequest renewalNurseServiceUpdateRequest
    ) {
        return customerNurseItemService.renewalNurseService(renewalNurseServiceUpdateRequest);
    }

    /**
     * 删除护理服务
     *
     * @param deleteRequestBody
     * @return
     */
    @PostMapping("/deleteNurseService")
    public BaseResponse<Page<BoughtNurseServiceVO>> deleteNurseService(
            @RequestBody @Validated DeleteRequestBody deleteRequestBody
    ) {
        return customerNurseItemService.deleteNurseService(deleteRequestBody);
    }

    /**
     * 添加指定客户护理服务
     *
     * @param customerNurseAddRequest
     * @return
     */
    @PostMapping("/addNurseService")
    public BaseResponse<String> addNurseService(
            @RequestBody @Validated List<CustomerNurseAddRequest> customerNurseAddRequest
    ) {
        return customerNurseItemService.addNurseService(customerNurseAddRequest);
    }
}
