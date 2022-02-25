package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginReqDto {
    private String userID;
    private String userPW;

}