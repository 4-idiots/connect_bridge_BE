package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import lombok.*;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import org.yaml.snakeyaml.events.Event;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@Getter
@Data

public class TeamProfileDto  {
    private Long id;
    private String userNickname;
    private String userName;
    private String userAbility;
    private String userArea;
    private String userTime;
    private String userInterest;
    private String userIntroduce;

    @Builder
    public TeamProfileDto(Long id, String userName, String userNickname, String userAbility, String userInterest, String userIntroduce, String userArea, String userTime){
        this.id = id;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userAbility = userAbility;
        this.userInterest = userInterest;
        this.userIntroduce =userIntroduce;
        this.userArea = userArea;
        this.userTime = userTime;
    }

    public TeamProfileDto(RegisterEntity registerEntity) {
        this.id = registerEntity.getId();
        this.userName = registerEntity.getUserName();
        this.userNickname = registerEntity.getUserNickname();
        this.userAbility = registerEntity.getUserAbility();
        this.userInterest = registerEntity.getUserInterest();
        this.userTime = registerEntity.getUserTime();
        this.userIntroduce = registerEntity.getUserIntroduce();
        this.userArea = registerEntity.getUserArea();
    }
    //private String userPicture;
    //private Long userLike;


}
