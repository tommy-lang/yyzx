package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName nurse_level_item
 */
@TableName(value ="nurse_level_item")
@Data
public class NurseLevelItem implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关联护理级别
     */
    private Integer levelId;

    /**
     * 关联护理项目
     */
    private Integer itemId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}