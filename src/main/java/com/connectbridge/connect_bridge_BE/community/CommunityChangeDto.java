package com.connectbridge.connect_bridge_BE.community;

import lombok.*;

import java.util.Arrays;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class CommunityChangeDto {
    private long postID;
    private String title;
    private List<String> hashtag;
    private List content;
    private long userID;

    public String convertStr(List<String> hashtag) { //DB에 저장
        String str = String.join(",", hashtag);
        return str;
    }
    public List<String> convertList(String str){
        return Arrays.asList((str.split(",")));}
}
