package com.htu.yyzx.common;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DeleteRequestBody {
    @Size(min = 1, message = "删除的id不能为空")
    private List<Long> ids;
}
