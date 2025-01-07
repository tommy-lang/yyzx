package com.htu.yyzx.model.enums;

import lombok.Getter;

@Getter
public enum ExecutionCycle {
    DAY("每天"),
    WEEK("每周"),
    MONTH("每月"),
    ;
    private final String message;
    ExecutionCycle(String message) {
        this.message = message;
    }
}
