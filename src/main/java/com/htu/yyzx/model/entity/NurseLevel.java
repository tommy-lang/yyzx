package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName nurse_level
 */
@TableName(value ="nurse_level")
@Data
public class NurseLevel implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 护理级别
     */
    private String levelName;

    /**
     * 级别状态 1：启用；2：停用
     */
    private Integer levelStatus;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}