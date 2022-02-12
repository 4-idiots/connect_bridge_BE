package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResDto {

    Long id;
    String userID;
    String userName;

    String userMessage;

    //String userEmail;

    @Builder
    public LoginResDto(Long id, String userID, String userName){
        this.id = id;
        this.userID = userID;
        this.userName = userName;
    }

}
