package com.htu.yyzx.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 用户 VO （脱敏）
 */
@Data
public class ListUserVO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GTM+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GTM+8", pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 真实姓名
     */
    private String nickname;
    /**
     * 系统账号
     */
    private String username;
    /**
     * 密码
     */
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
