package com.htu.yyzx.model.dto.customer;

import lombok.Data;

@Data
public class CustomerPreferenceUpdateRequest {
    /**
     * 喜好ID
     */
    private Integer id;

    /**
     * 顾客ID
     */
    private Integer customerId;

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