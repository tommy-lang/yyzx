package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName customer_preference
 */
@TableName(value ="customer_preference")
@Data
public class CustomerPreference implements Serializable {
    /**
     * 喜好ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 顾客ID
     */
    private Integer customerId;

    /**
     * 饮食喜好
     */
    private String preferences;

    /**
     * 注意事项
     */
    private String attention;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private Customer customer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}