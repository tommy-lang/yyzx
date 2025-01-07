package com.htu.yyzx.model.dto.customer;

import com.htu.yyzx.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckOutQueryRequest extends PageRequest {

    /**
     * 客户姓名
     */
    private String customerName;

}
