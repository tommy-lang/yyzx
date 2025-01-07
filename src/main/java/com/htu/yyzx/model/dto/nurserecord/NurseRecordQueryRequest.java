package com.htu.yyzx.model.dto.nurserecord;

import com.htu.yyzx.common.PageRequest;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NurseRecordQueryRequest extends PageRequest {
    /**
     * 客户 id
     */
    @Positive
    private Integer customerId;
}
