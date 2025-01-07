package com.htu.yyzx.model.dto.nurse;

import com.htu.yyzx.common.PageRequest;
import lombok.Data;

@Data
public class NurseContentQueryRequest extends PageRequest {
    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 名称
     */
    private String nursingName;
    /**
     * 级别名字
     */
    private String levelName;

    /**
     * 状态 1：启用；2：停用
     */
    private Integer status;
}
