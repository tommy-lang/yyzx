package com.htu.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.R;
import com.htu.yyzx.model.dto.nurse.NurseLevelQueryRequest;
import com.htu.yyzx.model.entity.NurseLevel;
import com.htu.yyzx.service.NurseContentService;
import com.htu.yyzx.service.NurseLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurse")
@RequiredArgsConstructor
public class NurseController {

    private final NurseLevelService nurseLevelService;
    private final NurseContentService nurseContentService;


    /**
     * 分页查询护理水平登记
     *
     * @param nurseLevelQueryRequest
     * @return
     */
    @PostMapping("/queryNurseLevel")
    public BaseResponse<Page<NurseLevel>> queryNurseLevel(
            @RequestBody NurseLevelQueryRequest nurseLevelQueryRequest
    ) {
        return nurseLevelService.queryNurseLevel(nurseLevelQueryRequest);
    }

    /**
     * 添加护理水平登记
     *
     * @param nurseLevel
     * @return
     */
    @PostMapping("/addNurseLevel")
    public BaseResponse<String> addNurseLevel(
            @RequestBody NurseLevel nurseLevel
    ) {
        return nurseLevelService.addNurseLevel(nurseLevel);
    }

    /**
     * 更新护理水平详细信息
     *
     * @param nurseLevel
     * @return
     */
    @PostMapping("/updateNurseLevel")
    public BaseResponse<Boolean> updateNurseLevel(
            @RequestBody NurseLevel nurseLevel
    ) {
        return R.success(nurseLevelService.updateById(nurseLevel));
    }

    /**
     * 删除护理水平
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/deleteNurseLevel")
    public BaseResponse<String> deleteNurseLevel(
            @RequestBody DeleteRequestBody deleteRequest
    ) {
        return nurseLevelService.deleteNurseLevel(deleteRequest);
    }

    /**
     * 根据护理级别名称获取护理项目(分页)
     *
     * @param levelName
     * @return
     */
    @PostMapping("/queryNurseContentByLevelName")
    public BaseResponse<String> queryNurseContentByLevelName(
            @RequestParam String levelName
    ) {
        return nurseContentService.queryNurseContentByLevelName(levelName);
    }
}
