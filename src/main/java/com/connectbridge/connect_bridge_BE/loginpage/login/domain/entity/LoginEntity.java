package com.connectbridge.connect_bridge_BE.loginpage.login.domain.entity;


import javax.persistence.*;


@Table(name = "users")
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    private Long id;

    @Column(name = "user_id") //
    private String userID;

    @Column(name = "user_pw")
    private String userPW;

    public LoginEntity(String userID, String userPW){
        this.userID = userID;
        this.userPW = userPW;
    }
}

