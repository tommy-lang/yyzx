package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.healthcare.BoughtOrNotNurseServiceQueryRequest;
import com.htu.yyzx.model.dto.nurse.NurseContentQueryRequest;
import com.htu.yyzx.model.entity.NurseContent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.model.entity.NurseLevelItem;

import java.util.List;

public interface NurseContentService extends IService<NurseContent> {

    BaseResponse<Page<NurseContent>> queryNurseContentbylevelName(NurseContentQueryRequest nurseContentQueryRequest);

    BaseResponse<Page<NurseContent>> queryNurseContent(NurseContentQueryRequest nurseContentQueryRequest);

    BaseResponse<String> addNurseContent(NurseContent nurseContent);

    BaseResponse<String> deleteNurseContent(DeleteRequestBody deleteRequest);

    BaseResponse<String> queryNurseContentByLevelName(String levelName);

    BaseResponse<String> deleteNurseCnstentByLevelIdAndItemId(NurseLevelItem nurseLevelItem);

    BaseResponse<List<NurseContent>> getNurseContentList(Integer id);

    /**
     * 根据用户id查询用户未购买的服务
     *
     * @param boughtOrNotNurseServiceQueryRequest
     * @return
     */
    BaseResponse<Page<NurseContent>> queryNotBoughtNurseService(BoughtOrNotNurseServiceQueryRequest boughtOrNotNurseServiceQueryRequest);
}
