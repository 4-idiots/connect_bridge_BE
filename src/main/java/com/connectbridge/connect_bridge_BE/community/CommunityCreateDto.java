package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class CommunityCreateDto {

    private long postID;
    private String title;
    private List<String> hashtag;
    private String contents;
    private long viewCount;
    private long likeCount;
    private long likeCounta;
    private long commentCount;
    private String userNickname; //fromUserId
    private long fromUserId;
    private long userID;

    public String convertStr(List<String> hashtag) { //DB에 저장
        String str = String.join(",", hashtag);
        return str;
    }
    public List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    } //DB에서 빼냄

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
                .userID(userID)
                .build();
    }


}