package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName back_down
 */
@TableName(value ="back_down")
@Data
public class BackDown implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    private Integer isDeleted;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 退住时间
     */
    @JsonFormat(timezone = "GTM+8", pattern = "yyyy-MM-dd")
    private Date retreatTime;

    /**
     * 退住类型 0-正常退住  1-死亡退住 2-保留床位
     */
    private Integer retreatType;

    /**
     * 退住原因
     */
    private String retreatReason;

    /**
     * 审批状态  0-已提交 1-同意  2-拒绝
     */
    private Integer auditStatus;

    /**
     * 审批人
     */
    private String auditPerson;

    /**
     * 审批时间
     */
    @JsonFormat(timezone = "GTM+8", pattern = "yyyy-MM-dd")
    private Date auditTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}