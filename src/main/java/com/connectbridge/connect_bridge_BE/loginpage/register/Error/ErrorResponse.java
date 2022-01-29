package com.connectbridge.connect_bridge_BE.loginpage.register.Error;

import lombok.Getter;
import lombok.Setter;

public class ErrorResponse {
    private String code;
    private String description;
    private String errorMessage;
    public ErrorResponse(String code, String description) {
        this.code = code; this.description = description;
    }
    public ErrorResponse(String code, String description, String errorMessage) {
        this.code = code;
        this.description = description;
        this.errorMessage = errorMessage;
    }
}
