package com.connectbridge.connect_bridge_BE.outsideactpage.data.entity;

import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.OutActDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.PostCreateDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Long id; // primary key

    @Column(name = "title")
    private String outActName; // 게시글 제목

    @Column(name = "image")
    private String outActImg; // 사진주소?

    @Column(name = "url")
    private String outActLink; // 대외 활동 사이트 주소

    @Column(name = "view")
    private int outActView;// 게시글 조회수

    @Column(name ="likes")
    private int outActLike; // 게시글 추천수

    @Column(name = "sub")
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

    //=== 내용 수정 ====//
    /*
    public void updateTitle(String outActName){
        this.outActName = outActName;
    }

    public void updateUrl(String outActLink){
        this.outActLink = outActLink;
    }
     */

