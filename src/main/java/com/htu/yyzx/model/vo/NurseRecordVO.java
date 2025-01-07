package com.htu.yyzx.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class NurseRecordVO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 护理项目
     */
    private String itemName;

    /**
     * 护理项目编号
     */
    private String SerialNumber;

    /**
     * 护理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date nursingTime;

    /**
     * 护理内容
     */
    private String nursingContent;

    /**
     * 护理数量
     */
    private Integer nursingCount;

    /**
     * 护理人员
     */
    private String userNickName;

    /**
     * 护理人员电话
     */
    private String contactTel;

}
