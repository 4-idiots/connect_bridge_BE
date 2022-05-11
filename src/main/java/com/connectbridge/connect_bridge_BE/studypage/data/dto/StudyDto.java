package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudyDto {
    Long studyID;
    Long userID;
    String studyName;
    String studyKeyward;
    String studyField;
    String studyArea;
    String studyOnOff;
    String studyMember;
    String studyEnd;
    String studyStart;
    String content;
    int projectLike;
    int projectView;
    LocalDateTime createDate;

    public StudyDto(StudyEntity studyEntity){
        this.studyID=studyEntity.getId();
        this.studyID = studyEntity.getUserID();
        this.studyName = studyEntity.getStudyName();
        this.studyField = studyEntity.getStudyField();
        this.studyKeyward = studyEntity.getStudyKeyward();
        this.studyField = studyEntity.getStudyField();
        this.studyArea = studyEntity.getStudyArea();
        this.studyOnOff = studyEntity.getStudyOnOff();
        this.studyMember = studyEntity.getStudyMember();
        this.studyEnd = studyEntity.getStudyEnd();
        this.studyStart = studyEntity.getStudyEnd();
        this.content = studyEntity.getContent();
        this.projectLike = studyEntity.getStudyLike();
        this.projectView = studyEntity.getStudyView();
        this.createDate = studyEntity.getCreateDate();
    }

}
