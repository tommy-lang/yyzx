package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.nurse.NurseLevelQueryRequest;
import com.htu.yyzx.model.entity.NurseLevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface NurseLevelService extends IService<NurseLevel> {

    BaseResponse<List<String>> queryAllLevel();

    BaseResponse<Page<NurseLevel>> queryNurseLevel(NurseLevelQueryRequest nurseLevelQueryRequest);

    BaseResponse<String> addNurseLevel(NurseLevel nurseLevel);

    BaseResponse<String> deleteNurseLevel(DeleteRequestBody deleteRequest);
}
