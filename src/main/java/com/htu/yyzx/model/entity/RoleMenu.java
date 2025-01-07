package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName role_menu
 */
@TableName(value = "role_menu")
@Data
public class RoleMenu implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色 id
     */
    private Integer roleId;
    /**
     * 菜单 id
     */
    private Integer menuId;
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