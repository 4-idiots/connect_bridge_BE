package com.connectbridge.connect_bridge_BE.community.comment;

import lombok.*;
import com.connectbridge.connect_bridge_BE.community.comment.CommentEntity;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String comment;
    private Long postID;
    private String userNickname;
    private LocalDateTime registerDate;
}
