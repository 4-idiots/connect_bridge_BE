package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.community.comment.CommentEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OrderBy("id")
    @JsonManagedReference
    @OneToMany(mappedBy = "postID", fetch = FetchType.EAGER) //커뮤니티 아이디
    private List<CommentEntity> commentList = new ArrayList<>();

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

    @Column(name = "post_user_id")
    private long userID;



    public void updateCommunity(long id, String title, String contents, String hashtag){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.hashtag = hashtag;
    }
    public void updateCommunityNickname(long id, String userNickname){
        this.id = id;
        this.userNickname = userNickname;
    }

    public void postcountup(long id, long viewCount){
        this.id = id;
        this.viewCount = viewCount;
    }
    public void likecountup(long id, long likeCount){
        this.id = id;
        this.likeCount = likeCount;
    }
    public void commentCount(long id, long commentCount){
        this.id = id;
        this.commentCount = commentCount;
    }

}
