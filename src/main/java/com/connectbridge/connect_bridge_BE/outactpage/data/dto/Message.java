package com.connectbridge.connect_bridge_BE.outactpage.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    String message;

    @Builder
    public Message(String message) {
        this.message = message;
    }

}
