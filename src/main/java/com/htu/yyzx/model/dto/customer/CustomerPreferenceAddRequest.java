package com.htu.yyzx.model.dto.customer;

import lombok.Data;

@Data
public class CustomerPreferenceAddRequest {

    /**
     * 客户姓名
     */
    private Integer customerId;
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

}
