package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudySubmitDto {
    Long studyID;
    Long userID;
    String field;
}
