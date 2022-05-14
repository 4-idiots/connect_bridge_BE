package com.connectbridge.connect_bridge_BE.community.comment;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "post_id")
    private CommunityEntity postID;

    @Column(name = "user_id")
    private long userID;

    @Column(name = "user_nickname")
    private String userNickname;

    public CommentEntity(long id, String comment, CommunityEntity postID, long userID, String userNickname) {
        this.id = id;
        this.comment = comment;
        this.postID = postID;
        this.userID = userID;
        this.userNickname = userNickname;
    }
    public void updateComment(long id, String comment){
        this.id = id;
        this.comment = comment;
    }
}
