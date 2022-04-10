package com.connectbridge.connect_bridge_BE.community.like;
import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class CommunityLikeDto {
    private Long id;
    private RegisterEntity fromUser;
    private CommunityEntity toPost;


}
