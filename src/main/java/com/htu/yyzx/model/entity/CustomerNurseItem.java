package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName customer_nurse_item
 */
@TableName(value ="customer_nurse_item")
@Data
@Builder
public class CustomerNurseItem implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 护理项目编号
     */
    private Integer itemId;

    /**
     * 客户编号
     */
    private Integer customerId;

    /**
     * 护理级别编号
     */
    private Integer levelId;

    /**
     * 购买数量
     */
    private Integer nurseNumber;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 服务购买日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date buyTime;

    /**
     * 服务到期日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date maturityTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}