package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto {
    private Long projectID;
    private Long userID;
    private String projectName;
    private String projectImg;
    private int projectView;
    private int projectLike;
    private boolean projectSub;
    private String projectField;
    private String projectArea;
    private boolean projectState;
    private String projectStart;
    private String projectEnd;
    private boolean projectMotive;
    private List projectContent;
    private boolean projectOnOff;
    private String projectTotal; // List로 변경
    private String projectReference;
    private List<String> projectPlatform; // List로 변경
    private String projectSkill;
    private LocalDateTime createDate;

}