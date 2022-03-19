package com.connectbridge.connect_bridge_BE.globalerror;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String message;

    public ErrorResponse(ErrorCode errorCode) {

        this.message = errorCode.getMessage();
    }
}
