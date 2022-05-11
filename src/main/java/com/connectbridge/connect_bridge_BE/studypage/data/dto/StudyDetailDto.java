package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;

public class StudyDetailDto {

    Long studyID;
    Long userID;
    String studyName;
    String studyKeyward;
    String studyField;
    String studyArea;
    String studyOnOff;
    String studyMember;//List로 줘야하고 Entity에는?
    String studyEnd;
    String studyStart;
    String content;

    public StudyDetailDto(StudyEntity studyEntity){
        this.studyID = studyEntity.getId();
        this.userID = studyEntity.getUserID();
        this.studyName = studyEntity.getStudyName();
        this.studyKeyward = studyEntity.getStudyKeyward();
        this.studyArea = studyEntity.getStudyArea();
        this.studyOnOff = studyEntity.getStudyOnOff();
        this.studyMember = studyEntity.getStudyMember();
        this.studyEnd = studyEntity.getStudyEnd();
        this.studyStart =studyEntity.getStudyStart();
        this.content =studyEntity.getContent();
    }
}
