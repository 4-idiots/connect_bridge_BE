package com.connectbridge.connect_bridge_BE.studypage.data.Entity;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.BaseTimeEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@DynamicUpdate
@Builder
@Table(name = "study")
public class StudyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "study_name")
    private String studyName;

    @Column(name = "study_keyward")
    private String studyKeyward;

    @Column(name = "study_field")
    private String studyField;

    @Column(name = "study_area")
    private String studyArea;

    @Column(name = "study_onoff")
    private String studyOnOff;

    @Column(name = "study_member")
    private String studyMember;

    @Column(name = "study_end")
    private String studyEnd;

    @Column(name = "study_start")
    private String studyStart;

    @Column(name = "study_like")
    private int studyLike;

    @Column(name = "study_view")
    private int studyView;

    private String content;


    public StudyEntity() {

    }

    public StudyEntity createStudy(StudyCreateDto createDto){
        return StudyEntity.builder()
                .userID(createDto.getUserID())
                .studyName(createDto.getStudyName())
                .studyKeyward(createDto.getStudyKeyward())
                .studyField(createDto.getStudyField())
                .studyArea(createDto.getStudyArea())
                .studyOnOff(createDto.getStudyOnOff())
                .studyMember(createDto.getStudyMember())
                .content(createDto.getContent())
                .build();
    }

/*
    public void updateLike(int studyLike){
        this.studyLike = studyLike;
    }
    public void updateView(int studyView){
        this.studyView = studyView;
    }

 */
}
