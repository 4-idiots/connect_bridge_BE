package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginReqDto {
    String userID;
    String userPW;

    @Builder
    LoginReqDto(String userID, String userPW){
            this.userID = userID;
            this.userPW = userPW;
    }
}
