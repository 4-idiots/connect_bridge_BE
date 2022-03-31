package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class DetailedDto {
    private Long userID;
    private String projectName;
    private int projectView;
    private int projectLike;
    private boolean projectState;
    private boolean projectMotive;
    private boolean projectSub;
    private String projectTotal; // List로 변경
    private String projectPlatform; // List로 변경
    private LocalDateTime createDate;
    private String projectImg;
    private String projectField;
    private String projectArea;
    private String projectStart;
    private String projectEnd;
    private String projectContent;
    private String projectOnOff;
    private String projectReference;
    private String projectSkill;
}
