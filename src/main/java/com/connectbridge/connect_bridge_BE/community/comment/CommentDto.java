package com.connectbridge.connect_bridge_BE.community.comment;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String comment;
    private Long postID;
}
