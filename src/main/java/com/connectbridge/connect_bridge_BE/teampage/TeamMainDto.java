package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data

public class TeamMainDto {
    private long myid;
    private String userNickname;
    private String userAbility;
    private String userInterestMain;
    private String userInterestSub;
    private String userIntroduce;
    private String userPicture;

    public TeamMainDto(RegisterEntity registerEntity) {
        this.myid = registerEntity.getId();
        this.userNickname = registerEntity.getUserNickname();
        this.userAbility = registerEntity.getUserAbility();
        this.userInterestMain = registerEntity.getUserInterestMain();
        this.userInterestSub = registerEntity.getUserInterestSub();
        this.userIntroduce = registerEntity.getUserIntroduce();
        this.userPicture = registerEntity.getUserPicture();
    }

    public TeamMainDto(BigInteger myid, String userNickname, String userAbility, String userInterestMain, String userInterestSub, String userIntroduce, String userPicture) {
        this.myid = myid.longValue();
        this.userNickname = userNickname;
        this.userAbility = userAbility;
        this.userInterestMain = userInterestMain;
        this.userInterestSub = userInterestSub;
        this.userIntroduce = userIntroduce;
        this.userPicture = userPicture;
    }
}