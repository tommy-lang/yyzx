package com.htu.yyzx.model.dto.HealthSereviceS;

import com.htu.yyzx.common.PageRequest;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HealthServiceSQueryRequets extends PageRequest {
    /**
     * 用户 id
     */
    @Positive
    private Integer userId;
}
