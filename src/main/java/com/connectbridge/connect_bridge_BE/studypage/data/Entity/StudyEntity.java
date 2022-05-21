package com.connectbridge.connect_bridge_BE.studypage.data.Entity;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.BaseTimeEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyCreateDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.UpdateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@DynamicUpdate
@Builder
@Table(name = "study")
public class StudyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "study_name")
    private String studyName;

    @Column(name = "study_keyward")
    private String studyKeyward;

    @Column(name = "study_field")
    private String studyField;

    @Column(name = "study_area")
    private String studyArea;

    @Column(name = "study_onoff")
    private boolean studyOnOff;

    @Column(name = "study_member")
    private int studyMember;

    @Column(name = "study_member_now")
    private int studyMemberNow;

    @Column(name = "study_end")
    private String studyEnd;

    @Column(name = "study_start")
    private String studyStart;

    @Column(name = "study_like")
    private int studyLike;

    @Column(name = "study_view")
    private int studyView;

    private String content;

    @Column(name = "study_img")
    private String studyImg;

    @Column(name = "study_online")
    private String studyOnline;

    public StudyEntity() {

    }

    public StudyEntity createStudy(StudyCreateDto createDto){
        return builder()
                .userID(createDto.getUserID())
                .studyName(createDto.getStudyName())
                .studyKeyward(createDto.getStudyKeyward())
                .studyField(createDto.getStudyField())
                .studyArea(createDto.getStudyArea())
                .studyMember(createDto.getStudyMember())
                .content(createDto.getContent())
                .studyImg(createDto.getStudyImg())
                .studyOnline(createDto.getStudyOnline())
                .build();
    }

    public void stuEntUpdate(StudyCreateDto createDto){
        this.studyName = createDto.getStudyName();
        this.studyKeyward = createDto.getStudyKeyward();
        this.studyField = createDto.getStudyField();
        this.studyArea = createDto.getStudyArea();
        this.studyMember = createDto.getStudyMember();// 아마 삭제할듯
        this.studyStart = createDto.getStudyStart();
        this.studyEnd = createDto.getStudyEnd();
        this.content = createDto.getContent();
        this.studyImg = createDto.getStudyImg();
        this.studyOnline =createDto.getStudyOnline();
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

    public void updateLike(int studyLike){
        this.studyLike = studyLike;
    }
    public void updateView(int studyView){
        this.studyView = studyView;
    }
    public void updateOnOff(boolean studyOnOff){this.studyOnOff = studyOnOff;}
    public void updateMemNow(int studyMemberNow){
        this.studyMemberNow = studyMemberNow;
    }
}
