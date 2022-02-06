package com.connectbridge.connect_bridge_BE.loginpage.login.data.entity;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.LoginDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "users")

public class LoginEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "user_id")
        private String userID;

        @Column(name = "user_pw")
        private String userPW;

        @Column(name = "user_name")
        private String userName;

        @Column(name = "user_email")
        private String userEmail;

}

