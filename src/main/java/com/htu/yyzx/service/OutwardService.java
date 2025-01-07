package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.model.dto.customer.OutwardApprovalRequest;
import com.htu.yyzx.model.dto.customer.OutwardQueryRequest;
import com.htu.yyzx.model.dto.customer.CheckOutRevokedRequest;
import com.htu.yyzx.model.dto.customer.OutwardRemarkRequest;
import com.htu.yyzx.model.entity.Outward;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.model.vo.OutwardVO;

public interface OutwardService extends IService<Outward> {

    BaseResponse<Page<OutwardVO>> queryOutwardDetails(OutwardQueryRequest outwardQueryRequest);

    BaseResponse<String> revokedOutward(CheckOutRevokedRequest revokedOutwardRequest);

    BaseResponse<String> approvalOutward(OutwardApprovalRequest outwardApprovalRequest);

    BaseResponse<String> remarkOutward(OutwardRemarkRequest outwardQueryRequest);
}
