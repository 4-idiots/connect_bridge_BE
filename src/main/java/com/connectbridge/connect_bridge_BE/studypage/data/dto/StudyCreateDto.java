package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    public StudyEntity studyEntity() {
        return StudyEntity.builder()
                .studyName(studyName)
                .studyKeyward(studyKeyward)
                .studyField(studyField)
                .studyArea(studyArea)
                .studyMember(studyMember)
                .studyMemberNow(studyMemberNow)
                .studyStart(studyStart)
                .studyEnd(studyEnd)
                .content(content)
                .studyImg(studyImg)
                .studyOnline(studyOnline)
                .build();
    }
}
