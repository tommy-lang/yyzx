package com.htu.yyzx.model.dto.nurse;

import com.htu.yyzx.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NurseLevelQueryRequest extends PageRequest {

    /**
     * 护理级别
     */
    private String levelName;

    /**
     * 级别状态 1：启用；2：停用
     */
    private Integer levelStatus;
}
