package com.htu.yyzx.model.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.htu.yyzx.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest {

    /**
     * 真实姓名
     */
    private String nickname;
    /**
     * 系统账号
     */
    private String username;

    /**
     * 性别（0：女  1：男）
     */
    private Integer sex;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话号码
     */
    private String phoneNumber;

    private Integer roleId;
}
