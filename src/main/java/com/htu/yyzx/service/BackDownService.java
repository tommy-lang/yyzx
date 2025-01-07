package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.model.dto.customer.*;
import com.htu.yyzx.model.entity.BackDown;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.model.vo.CheckOutVO;
public interface BackDownService extends IService<BackDown> {

    BaseResponse<Page<CheckOutVO>> queryCheckOutDetails(CheckOutQueryRequest checkOutQueryRequest);

    BaseResponse<String> revokedCheckOut(CheckOutRevokedRequest checkOutRevokedRequest);

    BaseResponse<String> approvalCheckOut(CheckOutApprovalRequest checkOutApprovalRequest);

    BaseResponse<String> remarkCheckOut(CheckOutRemarkRequest checkOutRemarkRequest);

    BaseResponse<Page<CheckOutVO>> queryOutwardDetails(OutwardQueryRequest outwardQueryRequest);
}
