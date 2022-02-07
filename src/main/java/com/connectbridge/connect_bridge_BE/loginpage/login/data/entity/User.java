package com.connectbridge.connect_bridge_BE.loginpage.login.data.entity;


import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
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
    //테스트 용 추가

    public User(Long id,String userID,String userPW, String userName){
        this.id = id;
        this.userID = userID;
        this.userPW = userPW;
        this.userName = userName;
    }

}

