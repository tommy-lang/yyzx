package com.htu.yyzx.model.dto.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class BedChangeUpdateRequest {
    /**
     * 主键
     */
    @Positive(message = "主键id必须为正数")
    private Integer id;
    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 新楼号
     */
    private String newFloorNo;
    /**
     * 新房间号
     */
    @NotBlank(message = "新房间号不能为空")
    private String newRoomNo;

    /**
     * 新床位号
     */
    @NotBlank(message = "新床位号不能为空")
    private String newBedNo;
    /**
     * 当前床位开始日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 当前床位结束日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endDate;

}
