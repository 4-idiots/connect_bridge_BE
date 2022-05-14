package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudyCreateDto {

    private Long userID; // 토큰 검증하고 set받음.
    private String studyName;
    private String studyKeyward;
    private String studyField;
    private String studyArea;
    private String studyMember; // 이거 안받음 지워야함.
    private String studyStart;
    private String studyEnd;
    private String content;
    private String studyStrImg; //Img받아서 변환후 set받음.
}
