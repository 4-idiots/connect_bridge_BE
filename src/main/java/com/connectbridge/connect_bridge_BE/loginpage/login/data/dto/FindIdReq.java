package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindIdReq {
    String userName;
    String userPhone;
    String userEmail;

    @Builder
    public FindIdReq(String userName, String userPhone, String userEmail){
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }
}