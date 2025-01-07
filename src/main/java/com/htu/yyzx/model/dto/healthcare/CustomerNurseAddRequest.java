package com.htu.yyzx.model.dto.healthcare;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CustomerNurseAddRequest {
    @Positive
    private Integer customerId;
    @Positive
    private Integer itemId;
}
