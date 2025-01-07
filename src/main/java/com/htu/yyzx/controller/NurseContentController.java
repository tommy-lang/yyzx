package com.htu.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.R;
import com.htu.yyzx.model.dto.nurse.NurseContentQueryRequest;
import com.htu.yyzx.model.entity.NurseContent;
import com.htu.yyzx.model.entity.NurseLevelItem;
import com.htu.yyzx.service.NurseContentService;
import com.htu.yyzx.service.NurseLevelItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurse")
@RequiredArgsConstructor
public class NurseContentController {

    private final NurseContentService nurseContentService;
    private final NurseLevelItemService nurseLevelItemService;

    /**
     * 分页查询护理水平登记
     *
     * @param nurseContentQueryRequest
     * @return
     */
    @PostMapping("/queryNurseContent")
    public BaseResponse<Page<NurseContent>> queryNurseContent(
            @RequestBody NurseContentQueryRequest nurseContentQueryRequest
    ) {
        return nurseContentService.queryNurseContent(nurseContentQueryRequest);
    }

    @PostMapping("/queryNurseContentBylevelName")
    public BaseResponse<Page<NurseContent>> queryNurseContentByLevelName(
            @RequestBody NurseContentQueryRequest nurseContentQueryRequest

    ) {
        System.out.println(nurseContentQueryRequest.toString());
        return nurseContentService.queryNurseContentbylevelName(nurseContentQueryRequest);
    }

    /**
     * 添加护理水平登记
     *
     * @param nurseContent
     * @return
     */
    @PostMapping("/addNurseContent")
    public BaseResponse<String> addNurseContent(
            @RequestBody NurseContent nurseContent
    ) {
        return nurseContentService.addNurseContent(nurseContent);
    }

    /**
     * 更新护理水平详细信息
     *
     * @param nurseContent
     * @return
     */
    @PostMapping("/updateNurseContent")
    public BaseResponse<Boolean> updateNurseContent(
            @RequestBody NurseContent nurseContent
    ) {
        System.out.println(nurseContent);
        return R.success(nurseContentService.updateById(nurseContent));
    }

    /**
     * 删除护理水平
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/deleteNurseContent")
    public BaseResponse<String> deleteNurseContent(
            @RequestBody DeleteRequestBody deleteRequest
    ) {
        return nurseContentService.deleteNurseContent(deleteRequest);
    }

    /**
     * 根据Levelid和itemId删除关联关系
     * @param nurseLevelItem
     * @return
     */
    @PostMapping("/deleteNurseCnstentByLevelIdAndItemId")
    public BaseResponse deleteNurseCnstentByLevelIdAndItemId(
            @RequestBody NurseLevelItem nurseLevelItem
            ){

        return nurseContentService.deleteNurseCnstentByLevelIdAndItemId(nurseLevelItem);
    }
    @GetMapping("/getNurseContentList")
    public BaseResponse getNurseContentList(
            Integer id
    ){
        BaseResponse<List<NurseContent>> nurseContentList = nurseContentService.getNurseContentList(id);
        return nurseContentList;
    }
    @PostMapping("/addNurseContent2")
    public BaseResponse AddNurseContent2(
            @RequestBody NurseLevelItem nurseLevelItem
    ){
        boolean save = nurseLevelItemService.save(nurseLevelItem);
        return R.success("添加护理项目成功");
    }
}
