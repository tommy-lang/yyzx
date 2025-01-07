package com.htu.yyzx.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName customer
 */
@TableName(value = "customer")
@Data
public class Customer implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    @TableLogic
    private Integer isDeleted;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 年龄
     */
    private Integer customerAge;
    /**
     * 性别  0：男  1：女
     */
    private Integer customerSex;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 房间号
     */
    private String roomNo;
    /**
     * 所属楼房
     */
    private String buildingNo;
    /**
     * 入住时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date checkinDate;
    /**
     * 合同到期时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date expirationDate;
    /**
     * 联系电话
     */
    private String contactTel;
    /**
     * 床号
     */
    private Integer bedId;
    /**
     * 身心状况
     */
    private String psychosomaticState;
    /**
     * 注意事项
     */
    private String attention;
    /**
     * 出生日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 身高
     */
    private String height;
    /**
     * 体重
     */
    private String weight;
    /**
     * 血型
     */
    private String bloodType;
    /**
     * 头像路径
     */
    private String filepath;
    /**
     * 关联系统健康管家(护工)  无管家设置  -1
     */
    private Integer userId;
    /**
     * 护理等级
     */
    private Integer levelId;
    /**
     * 家属
     */
    private String familyMember;


}