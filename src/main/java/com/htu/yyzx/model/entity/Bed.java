package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName bed
 */
@TableName(value = "bed")
@Data
public class Bed implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 房间号
     */
    private Integer roomNo;
    /**
     * 床位状态 1: 未使用 2: 已占用 3: 外出
     */
    private Integer status;
    /**
     * 备注
     */
    private Integer remarks;
    /**
     * 床位号
     */
    private String bedNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private Integer createBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新者
     */
    private Integer updateBy;
    /**
     * 是否删除
     */
    private Integer isDelete;

}