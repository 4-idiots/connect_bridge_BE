package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResDto {

    String accessToken;

    @Builder
    public TokenResDto(String accessToken){
        this.accessToken = accessToken;
    }
}