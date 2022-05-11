package com.connectbridge.connect_bridge_BE.loginpage.login.data.entity;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "user_pw")
    private String userPW;

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "user_Email")
    private String userEmail;

    @Column(name = "refresh_token")
    private String refreshToken;

    private boolean role;

    @Column(name = "user_introduce")
    private String introduce;

    @Column(name = "user_picture")
    private String picture;


    @Column(name = "user_ability")
    private String userAbility;

    @Column(name = "user_nickname")
    private String userNickName;

    @Column(name = "user_interest_sub")
    private String userInterestSub;

    //@Column(name = "user_interest_sub")
    //private String userInterestSub;

    //토큰 갱신
    public void updateToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    // 토큰 반환
    public void destroyRefreshToken(){
        this.refreshToken =null;
    }

    // 임시 비밀번호 설정.
    public void updateImplPw(String implPw){
        this.userPW = implPw;
    }

}
