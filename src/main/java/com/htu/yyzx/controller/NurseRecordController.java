
package com.htu.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.nurserecord.NurseRecordQueryRequest;
import com.htu.yyzx.model.vo.NurseRecordVO;
import com.htu.yyzx.service.CustomerNurseItemService;
import com.htu.yyzx.service.CustomerService;
import com.htu.yyzx.service.NurseContentService;
import com.htu.yyzx.service.NurseRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurse")
@RequiredArgsConstructor
public class NurseRecordController {

    private final CustomerService customerService;
    private final NurseRecordService nurseRecordService;
    private final CustomerNurseItemService customerNurseItemService;
    private final NurseContentService nurseContentService;

    /**
     * 分页查询客户护理记录
     *
     * @param nurseRecordQueryRequest
     * @return
     */
    @PostMapping("/queryNurseRecord")
    public BaseResponse<Page<NurseRecordVO>> queryNurseRecord(
            @RequestBody @Validated NurseRecordQueryRequest nurseRecordQueryRequest
    ) {
        return nurseRecordService.queryNurseRecord(nurseRecordQueryRequest);
    }

    /**
     * 删除客户护理记录
     *
     * @param deleteRequestBody
     * @return
     */
    @PostMapping("/deleteNurseRecord")
    public BaseResponse<Page<NurseRecordVO>> deleteNurseRecord(
            @RequestBody @Validated DeleteRequestBody deleteRequestBody
    ) {
        return nurseRecordService.deleteNurseRecord(deleteRequestBody);
    }

    /**
     * 删除客户护理记录(by 客户id)
     *
     * @param deleteRequestBody
     * @return
     */
    @PostMapping("/deleteNurseRecordByCustomerId")
    public BaseResponse<Page<NurseRecordVO>> deleteNurseRecordByCustomerId(
            @RequestBody @Validated DeleteRequestBody deleteRequestBody
    ) {
        return nurseRecordService.deleteNurseRecordByCustomerId(deleteRequestBody);
    }
}
