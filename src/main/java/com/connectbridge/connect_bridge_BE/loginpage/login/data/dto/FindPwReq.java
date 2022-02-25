package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindPwReq {

    private String userID;
    private String userName;
    private String userEmail;

}
