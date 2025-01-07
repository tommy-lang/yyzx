package com.htu.yyzx.common;

import lombok.Data;

/**
 * 通用返回类
 */
@Data
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
