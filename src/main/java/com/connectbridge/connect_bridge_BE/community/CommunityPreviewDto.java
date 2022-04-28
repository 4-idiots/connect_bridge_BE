package com.connectbridge.connect_bridge_BE.community;

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
public class CommunityPreviewDto {
    private long postID;
    private String tile;
    private List<String> hashtag;
    private String userNickname;
    private long viewCount;
    private long likeCount;
    private long commentCount;



    public List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    } //DB에서 빼냄

    public CommunityPreviewDto(BigInteger postID, String tile, String hashtag, String userNickname, BigInteger viewCount, BigInteger likeCount, BigInteger commentCount) {
        this.postID = postID.longValue();
        this.tile = tile;
        this.hashtag = convertList(String.valueOf(hashtag));
        this.userNickname = userNickname;
        this.viewCount = viewCount.longValue();
        this.likeCount = likeCount.longValue();
        this.commentCount = commentCount.longValue();
    }
}
