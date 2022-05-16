package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

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
    List content;
    String studyImg;
    int studyLike;
    int studyView;
    boolean studySub;
    String studyOnline;

    List memberID;
    HashMap leaderInfo;
    List memberList;

    public List jacksonMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        List map = null;
        try {
            map = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
    public StudyDetailDto(StudyEntity studyEntity) {
        this.studyID = studyEntity.getId();
        this.userID = studyEntity.getUserID();
        this.studyName = studyEntity.getStudyName();
        this.studyKeyward = studyEntity.getStudyKeyward();
        this.studyArea = studyEntity.getStudyArea();
        this.studyOnOff = studyEntity.isStudyOnOff();
        this.studyMember = studyEntity.getStudyMember();
        this.studyMemberNow = studyEntity.getStudyMemberNow();
        this.studyEnd = studyEntity.getStudyEnd();
        this.studyStart = studyEntity.getStudyStart();
        this.content = jacksonMap(studyEntity.getContent());
        this.studyImg = studyEntity.getStudyImg();
        this.studyLike = studyEntity.getStudyLike();
        this.studyView = studyEntity.getStudyView();
        this.studyField = studyEntity.getStudyField();
        this.studyOnline = studyEntity.getStudyOnline();
    }
}
