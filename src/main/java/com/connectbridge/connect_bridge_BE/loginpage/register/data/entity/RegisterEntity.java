package com.connectbridge.connect_bridge_BE.loginpage.register.data.entity;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")

public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "user_pw")
    private String userPW;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_birthday1")
    private String userBirthdayY;
    @Column(name = "user_birthday2")
    private String userBirthdayM;
    @Column(name = "user_birthday3")
    private String userBirthdayD;

    @Column(name = "user_gender")
    private Boolean userGender;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_ability")
    private String userAbility;

    @Column(name = "user_area")
    private String userArea;

    @Column(name = "user_time")
    private String userTime;

    @Column(name = "user_interest")
    private String userInterest;

    @Column(name = "user_introduce")
    private String userIntroduce;


    public RegisterDto toregisterDto() {
        return RegisterDto.builder()
                .id(id)
                .userPhone(userPhone)
                .userID(userID)
                .userPW(userPW)
                .userNickname(userNickname)
                .userName(userName)
                .userBirthdayY(userBirthdayY)
                .userBirthdayM(userBirthdayM)
                .userBirthdayD(userBirthdayD)
                .userGender(userGender)
                .userEmail(userEmail)
                .userAbility(userAbility)
                .userArea(userArea)
                .userTime(userTime)
                .userInterest(userInterest)
                .userIntroduce(userIntroduce)
                .build();
    }

    public RegisterEntity(String userNickname, String userName, String userAbility, String userArea, String userTime, String userInterest,String userIntroduce){
        this.userNickname= userNickname;
        this.userName = userName;
        this.userAbility = userAbility;
        this.userArea = userArea;
        this.userTime = userTime;
        this.userInterest = userInterest;
        this.userIntroduce = userIntroduce;
    }

}
