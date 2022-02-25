package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResDto {

    String accessToken;
    String refreshToken;

    //String userEmail;

    @Builder
    public LoginResDto(String accessToken, String refreshToken){
        this.accessToken =accessToken;
        this.refreshToken = refreshToken;
    }

    public void updateToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

}