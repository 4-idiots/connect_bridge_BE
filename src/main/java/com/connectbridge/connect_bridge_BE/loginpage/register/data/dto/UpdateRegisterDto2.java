package com.connectbridge.connect_bridge_BE.loginpage.register.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class UpdateRegisterDto2 {
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


}
