package com.idiotdb.loginpage.register.data.dto;

import com.idiotdb.loginpage.register.data.entity.RegisterEntity;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class RegisterDto {
    private Long ID;
    private String userPhone;
    private String userID;
    private String userPW;
    private String userNickname;
    private String userName;
    private String userBirthday;
    private Boolean userGender;
    private String userEmail;
    private Long userAbility;
    private Long userArea;
    private Long userTime;
    private Long userInterest;
    private String userIntroduce;


    public RegisterEntity toregisterEntity() {
        return RegisterEntity.builder()
                .id(ID)
                .userPhone(userPhone)
                .userID(userID)
                .userPW(userPW)
                .userNickname(userNickname)
                .userName(userName)
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
}
