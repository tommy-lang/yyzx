package com.htu.yyzx.model.enums;

public enum LevelStatus {
    DIE("启用", 1),
    RETAIN("停用", 2),
    ;
    private final String name;
    private final int code;

    LevelStatus(String name, int code) {
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
