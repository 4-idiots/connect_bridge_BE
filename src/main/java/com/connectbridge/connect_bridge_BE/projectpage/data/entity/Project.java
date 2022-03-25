
package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(name = "project")
@DynamicUpdate
@NoArgsConstructor
@Entity
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectID;

    @Column(name = "project_name")
    private String projectName; // 프로젝트 이름

    @Column(name = "project_motive")
    private boolean projectMotive; //프로젝트 목적(프로젝트, 스터디)

    @Column(name = "project_img")
    private String projectImg; // 프로젝트 사진

    @Column(name = "project_content")
    private String projectContent; // 프로젝트 설명

    @Column(name = "project_field")
    private String projectField; // 프로젝트 분야

    @Column(name = "project_onoff")
    private String projectOnOff; // 온, 오프라인 가능 여부

    @Column(name = "project_area")
    private String projectArea; // 프로젝트 가능 지역

    @Column(name = "project_total")
    private String projectTotal; // 프로젝트 모집인원 List로 받아야함.

    @Column(name = "project_reference")
    private String projectReference; // 참고자료

    @Column(name = "project_start")
    private String projectStart; // 프로젝트 시작일

    @Column(name= "project_end")
    private String projectEnd; // 프로젝트 종료일

    @Column(name = "project_platform")
    private String projectPlatform; // 출시 플렛폼 List롤 받아야함.

    @Column(name = "project_skill")
    private String projectSkill; // 프로젝트 언어

    @Column(name = "user_id")
    private Long userID; // user table의 user_id(pk)를 fk로 사용

    // 생성
    @Builder
    public Project(Long projectID, String projectName, boolean projectMotive, String projectImg, String projectContent, String projectField, String projectOnOff, String projectArea, String projectTotal, String projectReference, String projectStart, String projectEnd, String projectPlatform, String projectSkill, Long userID) {
        this.projectID=projectID;
        this.projectName = projectName;
        this.projectMotive = projectMotive;
        this.projectImg = projectImg;
        this.projectContent = projectContent;
        this.projectField = projectField;
        this.projectOnOff = projectOnOff;
        this.projectArea = projectArea;
        this.projectTotal = projectTotal;
        this.projectReference = projectReference;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.projectPlatform = projectPlatform;
        this.projectSkill = projectSkill;
        this.userID = userID;
    }
    // 수정정보
    @Builder
    public Project(String projectName, boolean projectMotive, String projectImg, String projectContent, String projectField, String projectOnOff, String projectArea, String projectTotal, String projectReference, String projectStart, String projectEnd){
        this.projectName = projectName;
        this.projectMotive = projectMotive;
        this.projectImg = projectImg;
        this.projectContent = projectContent;
        this.projectField = projectField;
        this.projectOnOff = projectOnOff;
        this.projectArea = projectArea;
        this.projectTotal = projectTotal;
        this.projectReference = projectReference;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
    }
}

