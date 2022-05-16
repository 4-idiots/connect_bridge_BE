package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import lombok.*;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import org.yaml.snakeyaml.events.Event;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Data

public class TeamProfileDto  {
    private Long myid;
    private String userNickname;
    private String userName;
    private String userAbility;
    private String userArea;
    private String userTime;
    private String userInterestMain;
    private String userInterestSub;
    private String userIntroduce;
    private String userPortfolio;
    private String userPicture;
    private Long follow;
    private String color;

    public TeamProfileDto(RegisterEntity registerEntity) {
        this.myid = registerEntity.getId();
        this.userName = registerEntity.getUserName();
        this.userNickname = registerEntity.getUserNickname();
        this.userAbility = registerEntity.getUserAbility();
        this.userInterestMain = registerEntity.getUserInterestMain();
        this.userInterestSub = registerEntity.getUserInterestSub();
        this.userTime = registerEntity.getUserTime();
        this.userIntroduce = registerEntity.getUserIntroduce();
        this.userArea = registerEntity.getUserArea();
        this.userPicture = registerEntity.getUserPicture();
        this.userPortfolio = registerEntity.getUserPortfolio();
    }


}
