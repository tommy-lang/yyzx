package com.htu.yyzx.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BoughtNurseServiceVO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 护理项目编号
     */
    private String serialNumber;

    /**
     * 护理项目
     */
    private String itemName;

    /**
     * 护理价格
     */
    private String price;

    /**
     * 护理级别
     */
    private String levelName;

    /**
     * 执行周期
     */
    private String executionCycle;

    /**
     * 购买数量
     */
    private Integer nurseNumber;

    /**
     * 服务购买日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date buyTime;

    /**
     * 服务到期日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date maturityTime;
}
