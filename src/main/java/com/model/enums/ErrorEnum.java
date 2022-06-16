package com.model.enums;

public enum ErrorEnum {
    ERROR(400,"业务异常！");

    private final int code;
    private final String msg;


    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
