package com.connectbridge.connect_bridge_BE.loginpage.login.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자의 접근제어 설정.
@Table(name = "users")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_phone", nullable = false)
    private String userPhone;

    @Column(name = "user_id", nullable = false)
    private String userID;

    @Column(name = "user_pw")
    private String userPW;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_birthday")
    private String userBirthday;

    @Column(name = "user_gender")
    private Boolean userGender;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_ability")
    private Long userAbility;

    @Column(name = "user_area")
    private Long userArea;

    @Column(name = "user_time")
    private Long userTime;

    @Column(name = "user_interest")
    private Long userInterest;

    @Column(name = "user_introduce")
    private String userIntroduce;

    private String role;

    //객체의 일관성을 보장하기위해 @Setter의 사용을 최소한으로 한다.
    //의미 있는 객체만 생성
    /* 근대 그냥 @setter 씀
    @Builder
    public MemberEntity(Long id, String userID, String userPW, String userEmail ){
        this.id = id;
        this.userID =userID;
        this.userPW = userPW;
        this.userEmail = userEmail;
    }
     */

    /*/
    Dto,Dv,Repository에 대한 개념 숙지.
     */
}