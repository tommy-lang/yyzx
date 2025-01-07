package com.htu.yyzx.model.enums;

public enum AuditStatus {
    NORMAL("已提交", 0),
    DIE("同意", 1),
    RETAIN("拒绝", 2),
    ;
    private final String name;
    private final int code;

    AuditStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
