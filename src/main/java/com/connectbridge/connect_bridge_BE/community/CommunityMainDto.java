package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class CommunityMainDto {

    private long postID;
    private String title;
    private List<String> hashtag;
    private List contents;
    private long viewCount;
    private long likeCount;
    private long commentCount;

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

    public CommunityMainDto(CommunityEntity communityEntity){
        this.postID = communityEntity.getId();
        this.title = communityEntity.getTitle();
        this.contents = jacksonMap(communityEntity.getContents());
        this.hashtag = convertList(communityEntity.getHashtag());
        this.viewCount = communityEntity.getViewCount();
        this.likeCount = communityEntity.getLikeCount();
        this.commentCount = communityEntity.getCommentCount();
    }

    public CommunityMainDto(BigInteger postID, String title, String hashtag, String contents, BigInteger viewCount, BigInteger likeCount, BigInteger commentCount) {
        this.postID = postID.longValue();
        this.title = title;
        this.hashtag = convertList(String.valueOf(hashtag));
        this.contents = jacksonMap(String.valueOf(contents));
        this.viewCount = viewCount.longValue();
        this.likeCount = likeCount.longValue();
        this.commentCount = commentCount.longValue();
    }
}
