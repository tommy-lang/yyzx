package com.htu.yyzx.model.dto.healthcare;

import com.htu.yyzx.common.PageRequest;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoughtOrNotNurseServiceQueryRequest extends PageRequest {
    /**
     * 客户 id
     */
    @Positive
    private Integer customerId;
}
