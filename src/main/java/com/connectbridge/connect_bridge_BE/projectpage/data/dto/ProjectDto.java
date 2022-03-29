package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
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
    private String projectPlatform; // List로 변경
    private String projectSkill;
    private LocalDateTime projectDate;

   public ProjectDto(Project project){
       this.projectID = project.getProjectID();
       this.userID = project.getUserID();
       this.projectName = project.getProjectName();
       this.projectView = project.getProjectView();
       this.projectLike = project.getProjectLike();
       this.projectImg = project.getProjectImg();
       this.projectSub = project.isProjectSub();
       this.projectField = project.getProjectField();
       this.projectState = project.isProjectState();
       this.projectArea = project.getProjectArea();
       this.projectStart = project.getProjectStart();
       this.projectEnd = project.getProjectEnd();
       this.projectMotive = project.isProjectMotive();
       this.projectContent = project.getProjectContent();
       this.projectOnOff = project.getProjectOnOff();
       this.projectTotal = project.getProjectTotal();
       this.projectReference = project.getProjectReference();
       this.projectPlatform = project.getProjectPlatform();
       this.projectSkill = project.getProjectSkill();
       this.projectDate = project.getCreateDate();
   }
}