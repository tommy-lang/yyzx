package com.htu.yyzx.model.enums;

import lombok.Getter;

public enum BedStatus {
    OCCUPATION("已入住", 1),
    VACANCY("空床", 2),
    OUT("外出", 3);
    //    MAINTENANCE("维修", 4);
    private final String name;
    private final int code;

    BedStatus(String name, int code) {
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
