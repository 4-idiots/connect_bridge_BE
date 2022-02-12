package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindIdRes {
    String message;
    String userID;

    @Builder
    public FindIdRes(String message, String userID){
        this.message = message;
        this.userID = userID;
    }
}
