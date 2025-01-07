package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName nurse_record
 */
@TableName(value ="nurse_record")
@Data
public class NurseRecord implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 护理项目ID
     */
    private Integer itemId;

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
     * 护理人员ID
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}