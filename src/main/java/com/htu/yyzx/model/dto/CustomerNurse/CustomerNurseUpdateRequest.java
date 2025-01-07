package com.htu.yyzx.model.dto.CustomerNurse;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CustomerNurseUpdateRequest {
    /**
     * 主键
     */
    @Positive
    private Integer id;

    /**
     * 主键
     */
    private String levelName;

}
