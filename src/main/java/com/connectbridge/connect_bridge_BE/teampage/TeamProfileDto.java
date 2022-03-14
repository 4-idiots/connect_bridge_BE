package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import lombok.*;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import org.yaml.snakeyaml.events.Event;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data

public class TeamProfileDto  {
    private RegisterDto registerDto;
    //private String userPicture;
    //private Long userLike;


}
