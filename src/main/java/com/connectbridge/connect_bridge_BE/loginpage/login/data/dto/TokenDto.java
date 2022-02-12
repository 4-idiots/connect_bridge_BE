package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenDto {
    String token;

    @Builder
    public TokenDto(String token){
        this.token = token;
    }
}
