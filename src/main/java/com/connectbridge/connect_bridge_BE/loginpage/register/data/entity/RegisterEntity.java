package com.connectbridge.connect_bridge_BE.loginpage.register.data.entity;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")

public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "user_pw")
    private String userPW;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_ability")
    private String userAbility;

    @Column(name = "user_area")
    private String userArea;

    @Column(name = "user_time")
    private String userTime;

    @Column(name = "user_interest_main")
    private String userInterestMain;
    @Column(name = "user_interest_sub")
    private String userInterestSub;

    @Column(name = "user_introduce")
    private String userIntroduce;

    @Column(name = "user_portfolio")
    private String userPortfolio;

    @Column(name = "user_picture")
    private String userPicture;

    @Column(name = "createdate")
    @CreatedDate
    private LocalDateTime createDate;

    public RegisterDto toregisterDto() {
        return RegisterDto.builder()
                .id(id)
                .userID(userID)
                .userPW(userPW)
                .userNickname(userNickname)
                .userName(userName)
                .userEmail(userEmail)
                .userAbility(userAbility)
                .userArea(userArea)
                .userTime(userTime)
                .userInterestMain(userInterestMain)
                .userInterestSub(userInterestSub)
                .userIntroduce(userIntroduce)
                .build();
    }

    public RegisterEntity(String userNickname, String userName, String userAbility, String userArea, String userTime, String userInterestMain ,String userInterestSub, String userIntroduce){
        this.userNickname= userNickname;
        this.userName = userName;
        this.userAbility = userAbility;
        this.userArea = userArea;
        this.userTime = userTime;
        this.userInterestMain = userInterestMain;
        this.userInterestSub = userInterestSub;
        this.userIntroduce = userIntroduce;
    }
    public void updateRegister(String userPW, String userNickname, String userAbility, String userArea, String userTime, String userInterestMain, String userInterestSub, String userIntroduce, String userPortfolio, String userPicture) {
        this.userPW = userPW;
        this.userNickname = userNickname;
        this.userAbility = userAbility;
        this.userArea = userArea;
        this.userTime = userTime;
        this.userInterestMain = userInterestMain;
        this.userInterestSub = userInterestSub;
        this.userIntroduce = userIntroduce;
        this.userPortfolio = userPortfolio;
        this.userPicture = userPicture;
    }

}
