package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.community.comment.CommentEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class CommunityDto {

    private long postID;
    private String title;
    private List<String> hashtag;
    private String contents;
    private long viewCount;
    private long likeCount;
    private long likeCounta;
    private long commentCount;
    private String userNickname; //fromUserId
    private String userAbility;
    private String userInterestMain;
    private String userInterestSub;
    private long fromUserId;
    private long state;
    private String color;
    private List<CommentEntity> commentList;


    public String convertStr(List<String> hashtag) { //DB에 저장
        String str = String.join(",", hashtag);
        System.out.println(str);
        return str;
    }
    public List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    } //DB에서 빼냄

    public CommunityDto(CommunityEntity communityEntity){
        this.postID = communityEntity.getId();
        this.title = communityEntity.getTitle();
        this.contents = communityEntity.getContents();
        this.hashtag = convertList(communityEntity.getHashtag());
        this.viewCount = communityEntity.getViewCount();
        this.likeCount = communityEntity.getLikeCount();
        this.likeCounta = communityEntity.getLikeCount();
        this.commentCount = communityEntity.getCommentCount();
        this.userNickname = communityEntity.getUserNickname();
        this.setCommentList(communityEntity.getCommentList());
    }
    public CommunityEntity communityEntity() {
        return CommunityEntity.builder()
                .id(postID)
                .title(title)
                .contents(contents)
                .hashtag(convertStr(hashtag))
                .viewCount(viewCount)
                .likeCount(likeCount)
                .commentCount(commentCount)
                .userNickname(userNickname)
                .commentList(commentList)
                .build();
    }

}
