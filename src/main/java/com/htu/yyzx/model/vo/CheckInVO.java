package com.htu.yyzx.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CheckInVO {

    /**
     * 主键
     */
    private Integer id;

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
    private String bedNo;
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
     * 关联系统健康管家(护工)  无管家设置  -1
     */
    private String username;
    /**
     * 家属
     */
    private String familyMember;

}
