package com.htu.yyzx.model.dto.CustomerNurse;

import com.htu.yyzx.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNurseQueryRequest extends PageRequest {

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * true 客户只存在护理记录中  false or null 客户存在或者护理记录中
     */
    private Boolean flag;

}
