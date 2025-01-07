package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName nurse_content
 */
@TableName(value ="nurse_content")
@Data
public class NurseContent implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 名称
     */
    private String nursingName;

    /**
     * 价格
     */
    private String servicePrice;

    /**
     *  描述
     */
    private String message;

    /**
     * 状态 1：启用；2：停用
     */
    private Integer status;

    /**
     * 执行周期
     */
    private String executionCycle;

    /**
     * 执行次数
     */
    private String executionTimes;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}