package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    String userID;
    String userPW;

    UserDto(String userID,String userPW){
            this.userID = userID;
            this.userPW = userPW;
    }
}
