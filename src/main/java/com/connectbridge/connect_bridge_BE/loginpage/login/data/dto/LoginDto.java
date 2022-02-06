package com.connectbridge.connect_bridge_BE.loginpage.login.data.dto;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.LoginEntity;
import lombok.*;

@Data
@Builder
public class LoginDto {
    private String userID;
    private String userPW;

    /*
    private Long id;
    private String userName;
    private String userPhone;
    private String userNickname;
    private String userBirthday;
    private Boolean userGender;
    private String userEmail;
    private String userAbility;
    private String userArea;
    private String userTime;
    private String userInterest;
    private String userIntroduce;
*/
    public LoginDto(String userID, String userPW){
        this.userID = userID;
        this.userPW = userPW;
    }
}
/*
    public LoginEntity toLoginEntity() {
        return LoginEntity.builder()
                .id(id)
                .userID(userID)
                .userPW(userPW)
                .userPhone(userPhone)
                .userName(userName)
                .userNickname(userNickname)
                .userBirthday(userBirthday)
                .userGender(userGender)
                .userEmail(userEmail)
                .userAbility(userAbility)
                .userArea(userArea)
                .userTime(userTime)
                .userInterest(userInterest)
                .userIntroduce(userIntroduce)
                .build();
    }
    */
