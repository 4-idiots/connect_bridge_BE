package com.connectbridge.connect_bridge_BE.loginpage.register.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class UpdateRegisterDto {

    private Long id;
    private String userPW;
    private String userNickname;
    private String userAbility;
    private String userArea;
    private String userTime;
    private String userInterestMain;
    private String userInterestSub;
    private String userIntroduce;
    private String userPortfolio;
    private String userPicture;
    private MultipartFile img;

    @Builder
    public UpdateRegisterDto(Long id, String userPW, String userNickname, String userAbility, String userArea, String userTime, String userInterestMain, String userInterestSub, String userIntroduce, String userPortfolio, String userPicture) {
        this.id = id;
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
