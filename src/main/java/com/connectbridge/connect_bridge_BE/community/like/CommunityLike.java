package com.connectbridge.connect_bridge_BE.community.like;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "communitylike")
public class CommunityLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "to_post_id")
    private Long toPostId;

    public CommunityLike createCommunityLike(Long fromUserId, Long toPostId) {
        return CommunityLike.builder()
                .fromUserId(fromUserId)
                .toPostId(toPostId)
                .build();
    }
}
