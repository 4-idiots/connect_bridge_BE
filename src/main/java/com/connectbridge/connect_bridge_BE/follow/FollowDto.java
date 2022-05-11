package com.connectbridge.connect_bridge_BE.follow;
import lombok.*;

import java.math.BigInteger;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class FollowDto {
    private long id;
    private String name;
    private String userNickname;
    public FollowDto(BigInteger id, String name, String userNickname) {
        this.id = id.longValue();
        this.name = name;
        this.userNickname =userNickname;
    }
}
