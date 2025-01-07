package com.htu.yyzx.model.enums;

public enum RetreatType {
    NORMAL("正常退住", 0),
    DIE("死亡退住", 1),
    RETAIN("保留床位", 2);
    //    MAINTENANCE("维修", 4);
    private final String name;
    private final int code;

    RetreatType(String name, int code) {
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
