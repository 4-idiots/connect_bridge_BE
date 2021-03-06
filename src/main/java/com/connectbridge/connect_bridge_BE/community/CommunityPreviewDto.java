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
    private String title;
    private List<String> hashtag;
    private String userNickname;
    private long viewCount;
    private long likeCount;
    private long commentCount;



    public static List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    } //DB์์ ๋นผ๋

    public CommunityPreviewDto(BigInteger postID, String title, String hashtag, String userNickname, BigInteger viewCount, BigInteger likeCount, BigInteger commentCount) {
        this.postID = postID.longValue();
        this.title = title;
        this.hashtag = convertList(String.valueOf(hashtag));
        this.userNickname = userNickname;
        this.viewCount = viewCount.longValue();
        this.likeCount = likeCount.longValue();
        this.commentCount = commentCount.longValue();
    }

    public static CommunityPreviewDto fromEntity(CommunityEntity communityEntity){
        return CommunityPreviewDto.builder()
                .postID(communityEntity.getId())
                .title(communityEntity.getTitle())
                .hashtag(convertList(communityEntity.getHashtag()))
                .userNickname(communityEntity.getUserNickname())
                .viewCount(communityEntity.getViewCount())
                .likeCount(communityEntity.getLikeCount())
                .commentCount(communityEntity.getCommentCount())
                .build();
    }

}
