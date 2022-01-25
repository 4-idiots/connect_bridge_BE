package com.connectbridge.connect_bridge_BE.loginpage.register.data.entity;

public enum StatusEnum {
    OK(200, "ok"),
    BAD_REQUEST(400, "no"),
    NOT_FOUND(404, "NOT FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR");

    int code;
    String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
