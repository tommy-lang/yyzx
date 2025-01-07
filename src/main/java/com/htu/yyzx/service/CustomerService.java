package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.PageRequest;
import com.htu.yyzx.model.dto.CustomerNurse.CustomerNurseQueryRequest;
import com.htu.yyzx.model.dto.CustomerNurse.CustomerNurseUpdateRequest;
import com.htu.yyzx.model.dto.HealthSereviceS.HealthAdminAddRequest;
import com.htu.yyzx.model.dto.HealthSereviceS.HealthServiceSQueryRequets;
import com.htu.yyzx.model.dto.customer.CheckInAddRequest;
import com.htu.yyzx.model.dto.customer.CheckInQueryRequest;
import com.htu.yyzx.model.dto.customer.CheckOutQueryRequest;
import com.htu.yyzx.model.dto.customer.CustomerDetailsQueryRequest;
import com.htu.yyzx.model.entity.Customer;
import com.htu.yyzx.model.vo.CheckInVO;
import com.htu.yyzx.model.vo.CustomerDetailsVO;
import com.htu.yyzx.model.vo.CustomerNurseVO;
import com.htu.yyzx.model.vo.NoPreferenceVO;

import java.util.List;

public interface CustomerService extends IService<Customer> {

    BaseResponse<Page<CheckInVO>> queryCheckIn(CheckInQueryRequest checkInQueryRequest);

    BaseResponse<String> deleteCheckIn(DeleteRequestBody deleteRequest);

    BaseResponse<String> addCheckIn(CheckInAddRequest checkInAddRequest);

    BaseResponse<Page<CustomerDetailsVO>> queryCustomerDetails(CustomerDetailsQueryRequest customerDetailsQueryRequest);

    BaseResponse<Page<CustomerDetailsVO>> queryOutwardCustomer(CustomerDetailsQueryRequest customerDetailsQueryRequest);

    BaseResponse<List<String>> queryCustomerName(CheckOutQueryRequest checkOutQueryRequest);

    BaseResponse<NoPreferenceVO> queryCustomerOfNoPreference(String customerName);

    BaseResponse<Page<CustomerNurseVO>> queryCustomerNurse(CustomerNurseQueryRequest customerNurseQueryRequest);

    BaseResponse<Page<CustomerNurseVO>> updateCustomerNurse(CustomerNurseUpdateRequest customerNurseUpdateRequest);

    /**
     * 分页查询指定用户服务的客户
     *
     * @param healthServiceSQueryRequets
     * @return
     */
    BaseResponse<Page<CustomerDetailsVO>> queryCustomerByHealthAdmin(HealthServiceSQueryRequets healthServiceSQueryRequets);

    BaseResponse<String> deleteServiceS(DeleteRequestBody deleteRequestBody);

    BaseResponse<Page<CustomerDetailsVO>> queryCustomerByNoHealthAdmin(PageRequest pageRequest);

    BaseResponse<String> addHealthAdmin(List<HealthAdminAddRequest> healthAdminAddRequest);

    BaseResponse<Page<CustomerDetailsVO>> queryCustomerList(CustomerNurseQueryRequest customerNurseQueryRequest);
}
