package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.customer.CustomerPreferenceAddRequest;
import com.htu.yyzx.model.dto.customer.CustomerPreferenceQueryRequest;
import com.htu.yyzx.model.dto.customer.CustomerPreferenceUpdateRequest;
import com.htu.yyzx.model.entity.CustomerPreference;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.model.vo.CustomerPreferenceVO;

public interface CustomerPreferenceService extends IService<CustomerPreference> {

    BaseResponse<Page<CustomerPreferenceVO>> queryCustomerPreference(CustomerPreferenceQueryRequest customerPreferenceQueryRequest);

    BaseResponse<String> deleteCustomerPreference(DeleteRequestBody deleteRequestBody);

    BaseResponse<Page<CustomerPreferenceVO>> addCustomerPreference(CustomerPreferenceAddRequest customerPreferenceAddRequest);

    BaseResponse<Page<CustomerPreferenceVO>> updateCustomerPreference(CustomerPreferenceUpdateRequest customerPreferenceUpdateRequest);
}
