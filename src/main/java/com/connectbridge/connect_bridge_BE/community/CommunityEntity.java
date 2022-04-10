package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "community")
public class CommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_contents")
    private String contents;

    @Column(name = "post_hashtag")
    private String hashtag;

    @Column(name = "post_viewcount")
    private long viewCount;

    @Column(name = "post_likecount")
    private long likeCount;

    @Column(name = "post_commentcount")
    private long commentCount;

    @Column(name = "post_user_nickname")
    private String  userNickname;




}
