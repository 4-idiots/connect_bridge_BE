package com.connectbridge.connect_bridge_BE.community;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class MyCommunityDto {
    private long postID;
    private String title;
    private List<String> hashtag;
    private List contents;
    private String userNickname;
    private long viewCount;
    private long likeCount;
    private long commentCount;

    public static List jacksonMap(String json) {
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


    public static List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    } //DB์์ ๋นผ๋


    public static MyCommunityDto fromEntity2(CommunityEntity communityEntity){
        return MyCommunityDto.builder()
                .postID(communityEntity.getId())
                .title(communityEntity.getTitle())
                .hashtag(convertList(communityEntity.getHashtag()))
                .contents(jacksonMap(communityEntity.getContents()))
                .userNickname(communityEntity.getUserNickname())
                .viewCount(communityEntity.getViewCount())
                .likeCount(communityEntity.getLikeCount())
                .commentCount(communityEntity.getCommentCount())
                .build();
    }
}
