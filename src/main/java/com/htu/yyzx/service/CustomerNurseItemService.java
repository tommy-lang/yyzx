package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.healthcare.BoughtOrNotNurseServiceQueryRequest;
import com.htu.yyzx.model.dto.healthcare.CustomerNurseAddRequest;
import com.htu.yyzx.model.dto.healthcare.RenewalNurseServiceUpdateRequest;
import com.htu.yyzx.model.entity.CustomerNurseItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.model.vo.BoughtNurseServiceVO;

import java.util.List;

public interface CustomerNurseItemService extends IService<CustomerNurseItem> {

    BaseResponse<Page<BoughtNurseServiceVO>> queryBoughtNurseService(BoughtOrNotNurseServiceQueryRequest boughtOrNotNurseServiceQueryRequest);

    BaseResponse<Page<BoughtNurseServiceVO>> renewalNurseService(RenewalNurseServiceUpdateRequest renewalNurseServiceUpdateRequest);

    BaseResponse<Page<BoughtNurseServiceVO>> deleteNurseService(DeleteRequestBody deleteRequestBody);

    BaseResponse<String> addNurseService(List<CustomerNurseAddRequest> customerNurseAddRequest);
}
