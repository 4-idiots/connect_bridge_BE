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
@Table(name = "communitylike")
public class CommunityLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private RegisterEntity fromUser;

    @JoinColumn(name = "to_post_id")
    @ManyToOne
    private CommunityEntity toPost;

    @Builder
    public CommunityLike(RegisterEntity fromUser, CommunityEntity toPost) {
        this.fromUser = fromUser;
        this.toPost = toPost;
    }


}
