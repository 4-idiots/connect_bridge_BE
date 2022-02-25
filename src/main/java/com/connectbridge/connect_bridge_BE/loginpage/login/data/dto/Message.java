package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Message {
    private final String message;

    @Builder
    public Message(String message, Boolean value) {
        this.message = message;
    }
}
