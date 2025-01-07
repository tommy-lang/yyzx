package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName bed_details
 */
@TableName(value = "bed_details")
@Data
@Builder
public class BedDetails implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 开始日期
     */
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    /**
     * 结束日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    /**
     *
     */
    private String bedDetails;
    /**
     * 客户id
     */
    private Integer customerId;
    /**
     * 床位id
     */
    private Integer bedId;

    /**
     * 是否删除（自主实现逻辑删除）
     */
    private Integer isDelete;
}