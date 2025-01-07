package com.htu.yyzx.model.vo;

import lombok.Data;

@Data
public class CustomerNurseVO {
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
     * 房间号
     */
    private String roomNo;
    /**
     * 所属楼房
     */
    private String buildingNo;

    /**
     * 联系电话
     */
    private String contactTel;
    /**
     * 床号
     */
    private String bedNo;

    /**
     * 头像路径
     */
    private String filepath;

    /**
     * 护理等级
     */
    private String levelName;

}
