package com.connectbridge.connect_bridge_BE.outactpage.data.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@Table(name = "post")
public class OutAct extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id; // primary key

    @Column(name = "post_name")
    private String outActName; // 게시글 제목

    @Column(name = "post_image")
    private String outActImg; // 사진주소?

    @Column(name = "post_link")
    private String outActLink; // 대외 활동 사이트 주소

    @Column(name = "post_view")
    private int outActView;// 게시글 조회수

    @Column(name ="post_likes")
    private int outActLike; // 게시글 추천수

    @Column(name = "post_sub")
    private Boolean outActSub; //  추천 눌렀는지 확인

    public void createPost(String outActName,String outActImg,String outActLink){
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActLink = outActLink;
    }

    public void updatePost(String outActName,String outActImg, String outActLink){
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActLink = outActLink;
    }

}