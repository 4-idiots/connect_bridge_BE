package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenChecker {

    Long id;
    String userID;
    String userName;
    String refreshToken;

    @Builder
    public TokenChecker(Long id, String userID, String userName,String refreshToken){
        this.id = id;
        this.userID = userID;
        this.userName = userName;
        this.refreshToken = refreshToken;
    }
}
