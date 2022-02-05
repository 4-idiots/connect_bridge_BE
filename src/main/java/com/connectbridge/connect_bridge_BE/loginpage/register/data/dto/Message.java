package com.connectbridge.connect_bridge_BE.loginpage.register.data.dto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    private String message;

    @Builder
    public Message(String message){
        this.message = message;
    }
}
