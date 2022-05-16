package com.connectbridge.connect_bridge_BE.studypage.data.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "studynotice")
@Entity
public class StudyNoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "study_id")
    private Long studyID;

    @Column(name = "notice_content")
    private String content;

    public StudyNoticeEntity createNotice(Long studyID,String content){
        return builder()
                .studyID(studyID)
                .content(content)
                .build();
    }

}
