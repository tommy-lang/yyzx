package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName menu
 */
@TableName(value = "menu")
@Data
public class Menu implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单级别
     */
    private Integer menuIndex;
    /**
     * 菜单名称
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 路径
     */
    private String path;
    /**
     * 副菜单 id
     */
    private Integer parentId;
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