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
@Table(name = "users")
@DynamicUpdate
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_uid")
    private String userID;

    @Column(name = "user_pw")
    private String userPW;

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "user_Phone")
    private String userPhone;

    @Column(name = "user_Email")
    private String userEmail;

    @Column(name = "refresh_token")
    private String refreshToken;

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
