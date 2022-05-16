package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StudyDto {
    Long studyID;
    Long userID;
    String studyName;
    String studyKeyward;
    String studyField;
    String studyArea;
    boolean studyOnOff;
    int studyMember;
    int studyMemberNow;
    String studyEnd;
    String studyStart;
    List content;
    int studyLike;
    int studyView;
    LocalDateTime createDate;

    String studyOnline;
    String studyImg;

    public StudyDto(BigInteger studyID, BigInteger userID, String studyName, String studyKeyward, String studyField, String studyArea, boolean studyOnOff, int studyMember, int studyMemberNow, String studyEnd, String studyStart, String content, int studyLike, int studyView, Timestamp createDate, String studyOnline, String studyImg) {
        this.studyID = studyID.longValue();
        this.userID = userID.longValue();
        this.studyName = studyName;
        this.studyKeyward = studyKeyward;
        this.studyField = studyField;
        this.studyArea = studyArea;
        this.studyOnOff = studyOnOff;
        this.studyMember = studyMember;
        this.studyMemberNow = studyMemberNow;
        this.studyEnd = studyEnd;
        this.studyStart = studyStart;
        this.content = jacksonMap(String.valueOf(content));
        this.studyLike = studyLike;
        this.studyView = studyView;
        this.createDate = createDate.toLocalDateTime();
        this.studyOnline = studyOnline;
        this.studyImg = studyImg;
    }

    public List jacksonMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        //List<Map<Object, Object>> map = mapper.readValue(json, List.class);
        List map = null;
        try {
            map = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
    public StudyDto(StudyEntity studyEntity){
        this.studyID= studyEntity.getId();
        this.userID = studyEntity.getUserID();
        this.studyName = studyEntity.getStudyName();
        this.studyField = studyEntity.getStudyField();
        this.studyKeyward = studyEntity.getStudyKeyward();
        this.studyField = studyEntity.getStudyField();
        this.studyArea = studyEntity.getStudyArea();
        this.studyMember = studyEntity.getStudyMember();
        this.studyMemberNow = studyEntity.getStudyMemberNow();
        this.studyEnd = studyEntity.getStudyEnd();
        this.studyStart = studyEntity.getStudyEnd();
        this.content = jacksonMap(studyEntity.getContent());
        this.studyLike = studyEntity.getStudyLike();
        this.studyView = studyEntity.getStudyView();
        this.createDate = studyEntity.getCreateDate();
        this.studyImg = studyEntity.getStudyImg();
        this.studyOnline = studyEntity.getStudyOnline();
    }

}
