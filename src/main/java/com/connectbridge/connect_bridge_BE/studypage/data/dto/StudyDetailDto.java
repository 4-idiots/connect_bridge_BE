package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class StudyDetailDto {

    Long studyID;
    Long userID;
    String studyName;
    String studyKeyward;
    String studyField;
    String studyArea;
    boolean studyOnOff;
    int studyMember;//List로 줘야하고 Entity에는?
    int studyMemberNow;
    String studyEnd;
    String studyStart;
    String content;
    String studyImg;
    int studyLike;
    int studyView;
    boolean studySub;

    List memberID;
    HashMap leaderInfo;
    List memberList;

    public StudyDetailDto(StudyEntity studyEntity){
        this.studyID = studyEntity.getId();
        this.userID = studyEntity.getUserID();
        this.studyName = studyEntity.getStudyName();
        this.studyKeyward = studyEntity.getStudyKeyward();
        this.studyArea = studyEntity.getStudyArea();
        this.studyOnOff = studyEntity.isStudyOnOff();
        this.studyMember = studyEntity.getStudyMember();
        this.studyMemberNow = studyEntity.getStudyMemberNow();
        this.studyEnd = studyEntity.getStudyEnd();
        this.studyStart =studyEntity.getStudyStart();
        this.content =studyEntity.getContent();
        this.studyImg = studyEntity.getStudyImg();
        this.studyLike = studyEntity.getStudyLike();
        this.studyView = studyEntity.getStudyView();
    }
}
