package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyCreateDto {

    Long userID; // 이거 있어야 누가 만들었는지 알지. notion 추가 안 했음.
    String studyName;
    String studyKeyward;
    String studyField;
    String studyArea;
    String studyOnOff;
    String studyMember;
    String studyEnd;
    String studyStart;
    String content;
}
