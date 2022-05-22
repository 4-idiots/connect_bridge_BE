package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.community.comment.CommentEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.time.LocalDateTime;
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
    private List contents;
    private long viewCount;
    private long likeCount;
    private long likeCounta;
    private long commentCount;
    private long userID;
    private String userNickname; //fromUserId
    private String userAbility;
    private String userInterestMain;
    private String userInterestSub;
    private String userPicuture;
    private Boolean state;
    private List<CommentEntity> commentList;
    private LocalDateTime createDate;

    public List jacksonMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        //List<Map<Object, Object>> map = mapper.readValue(json, List.class);
        List map = null;
        try {
            map = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
    public String convertStr(List<String> hashtag) { //DB에 저장
        String str = String.join(",", hashtag);
        return str;
    }
    public List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    } //DB에서 빼냄

    public CommunityDto(CommunityEntity communityEntity){
        this.postID = communityEntity.getId();
        this.title = communityEntity.getTitle();
        this.contents = jacksonMap(communityEntity.getContents());
        this.hashtag = convertList(communityEntity.getHashtag());
        this.viewCount = communityEntity.getViewCount();
        this.likeCount = communityEntity.getLikeCount();
        this.likeCounta = communityEntity.getLikeCount();
        this.commentCount = communityEntity.getCommentCount();
        this.userNickname = communityEntity.getUserNickname();
        this.setCommentList(communityEntity.getCommentList());
        this.createDate = communityEntity.getCreateDate();
    }


}
