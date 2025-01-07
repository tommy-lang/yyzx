package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.nurserecord.NurseRecordQueryRequest;
import com.htu.yyzx.model.entity.NurseRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.model.vo.NurseRecordVO;

public interface NurseRecordService extends IService<NurseRecord> {

    BaseResponse<Page<NurseRecordVO>> queryNurseRecord(NurseRecordQueryRequest nurseRecordQueryRequest);

    BaseResponse<Page<NurseRecordVO>> deleteNurseRecord(DeleteRequestBody deleteRequestBody);

    BaseResponse<Page<NurseRecordVO>> deleteNurseRecordByCustomerId(DeleteRequestBody deleteRequestBody);
}
