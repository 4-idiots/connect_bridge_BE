package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Data

public class TeamMainDto {
    private Long myid;
    private String userNickname;
    private String userAbility;
    private String userInterestMain;
    private String userInterestSub;

    public TeamMainDto(RegisterEntity registerEntity) {
        this.myid = registerEntity.getId();
        this.userNickname = registerEntity.getUserNickname();
        this.userAbility = registerEntity.getUserAbility();
        this.userInterestMain = registerEntity.getUserInterestMain();
        this.userInterestSub = registerEntity.getUserInterestSub();
    }
}