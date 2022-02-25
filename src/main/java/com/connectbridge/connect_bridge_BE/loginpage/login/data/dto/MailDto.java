package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDto {

    private String title;
    private String message;
    private String userEmail;
    private String userID;

    private String implPw;

}
