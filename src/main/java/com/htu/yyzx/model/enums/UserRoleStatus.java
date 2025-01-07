package com.htu.yyzx.model.enums;

public enum UserRoleStatus {
    ADMIN("admin", 1),
    BUTLER("health-admin", 2),
    NO_BUTLER("visitor", -1);
    //    MAINTENANCE("维修", 4);
    private final String name;
    private final int code;

    UserRoleStatus(String name, int code) {
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
