package com.connectbridge.connect_bridge_BE.studypage.data.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDto {
    private Long studyID;
    private Long userID;
    private String studyName;
    private String studyKeyward;
    private String studyField;
    private String studyArea;
    private String studyStart;
    private String studyEnd;
    private String content;
    private String studyImg; //s3업로드 x
    private int studyMember; // 이거 안받음 지워야함.
    private int studyMemberNow;
    private String studyOnline;
}
