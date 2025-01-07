package com.htu.yyzx.model.vo;

import lombok.Data;

@Data
public class CustomerDetailsVO {
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
     * 床号
     */
    private String bedNo;

    /**
     * 护理级别
     */
    private String levelName;
}
