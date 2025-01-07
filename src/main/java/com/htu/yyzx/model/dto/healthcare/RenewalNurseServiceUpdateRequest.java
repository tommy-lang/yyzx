package com.htu.yyzx.model.dto.healthcare;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RenewalNurseServiceUpdateRequest {
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
     * 原有数量
     */
    private Integer nurseNumber;

    /**
     * 新增数量
     */
    private Integer addedNumber;

    /**
     * 服务到期日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date maturityTime;
}
