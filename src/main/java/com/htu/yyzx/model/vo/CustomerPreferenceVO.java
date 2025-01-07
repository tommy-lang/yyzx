package com.htu.yyzx.model.vo;

import com.htu.yyzx.model.entity.Customer;
import lombok.Data;

@Data
public class CustomerPreferenceVO {

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
     * 饮食喜好
     */
    private String preferences;

    /**
     * 注意事项
     */
    private String attention;

    /**
     * 备注
     */
    private String remark;


    private Customer customer;

}