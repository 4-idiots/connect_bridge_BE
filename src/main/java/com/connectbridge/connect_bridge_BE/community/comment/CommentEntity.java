package com.connectbridge.connect_bridge_BE.community.comment;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "post_id")
    private Long postID;
}
