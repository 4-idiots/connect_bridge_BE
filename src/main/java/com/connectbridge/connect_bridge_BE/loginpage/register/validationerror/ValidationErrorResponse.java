package com.connectbridge.connect_bridge_BE.loginpage.register.validationerror;

public class ValidationErrorResponse {
    private String code;
    private String description;
    private String errorMessage;
    public ValidationErrorResponse(String code, String description) {
        this.code = code; this.description = description;
    }
    public ValidationErrorResponse(String code, String description, String errorMessage) {
        this.code = code;
        this.description = description;
        this.errorMessage = errorMessage;
    }
}
