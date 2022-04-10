package com.connectbridge.connect_bridge_BE.follow;
import lombok.*;



@Builder
@AllArgsConstructor
@Getter
@Data
public class FollowDto {
    private Long id;
    private String name;
    private Long fromUser;
    private Long toUser;
}
