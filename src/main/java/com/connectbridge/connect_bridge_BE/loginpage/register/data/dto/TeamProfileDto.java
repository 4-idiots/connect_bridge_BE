package com.connectbridge.connect_bridge_BE.loginpage.register.data.dto;

import lombok.*;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data

public class TeamProfileDto {
    private RegisterDto registerDto;
    //private String userPicture;
    //private Long userLike;
}
