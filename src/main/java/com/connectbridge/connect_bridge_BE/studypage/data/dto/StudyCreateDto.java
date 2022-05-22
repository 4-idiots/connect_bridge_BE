package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StudyCreateDto {

    private Long userID;
    private String studyName;
    private String studyKeyward;
    private String studyField;
    private String studyArea;
    private int studyMember; // 이거 안받음 지워야함.
    private int studyMemberNow;
    private String studyStart;
    private String studyEnd;
    private String content;
    private String studyImg; //s3업로드 x
    private String studyOnline;

    public void createDto(UpdateDto dto){
        setUserID(dto.getUserID());
        setStudyName(dto.getStudyName());
        setStudyKeyward(dto.getStudyKeyward());
        setStudyField(dto.getStudyField());
        setStudyArea(dto.getStudyArea());
        setStudyMember(dto.getStudyMember());
        setStudyMemberNow(dto.getStudyMemberNow());
        setStudyStart(dto.getStudyStart());
        setStudyEnd(dto.getStudyEnd());
        setContent(dto.getContent());
        setStudyImg(dto.getStudyImg());
        setStudyOnline(dto.getStudyOnline());
    }
}
