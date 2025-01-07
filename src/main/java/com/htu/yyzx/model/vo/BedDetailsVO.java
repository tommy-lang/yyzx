package com.htu.yyzx.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BedDetailsVO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 开始日期
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    /**
     * 结束日期
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 客户姓名
     */
    private String customer;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 床位详情
     */
    private String bedDetails;

}
