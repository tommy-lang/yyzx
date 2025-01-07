package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.htu.yyzx.constant.PasswordSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 创建者
     */
    private Integer createBy;
    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateTime;
    /**
     * 更新者
     */
    private Integer updateBy;
    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @TableLogic
    private Integer isDelete;
    /**
     * 真实姓名
     */
    private String nickname;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 系统账号
     */
    @NotBlank(message = "账号不能为空")
    private String username;
    /**
     * 密码
     */
    @Size(
            min = PasswordSize.PASSWORD_MIN_SIZE, max = PasswordSize.PASSWORD_MAX_SIZE,
            message = "密码长度为" + PasswordSize.PASSWORD_MIN_SIZE + "-" + PasswordSize.PASSWORD_MAX_SIZE + "位"
    )
    private String password;
    /**
     * 性别（0：女  1：男）
     */
    private Integer sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 系统角色编号（1-管理员 2-健康管家）
     */
    private Integer roleId;
}