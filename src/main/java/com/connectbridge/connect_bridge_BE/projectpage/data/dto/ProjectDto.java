package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    private String projectContent;
    private String projectOnOff;
    private String projectTotal; // List로 변경
    private String projectReference;
    private List<String> projectPlatform; // List로 변경
    private String projectSkill;
    private LocalDateTime createDate;

}