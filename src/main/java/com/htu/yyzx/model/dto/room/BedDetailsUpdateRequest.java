package com.htu.yyzx.model.dto.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class BedDetailsUpdateRequest {

    /**
     * 主键
     */
    @Positive
    private Integer id;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户性别
     */
    private Integer customerSex;

    /**
     * 开始日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 结束日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endDate;

}
