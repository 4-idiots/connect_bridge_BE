package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DetailedDto {
    private Long userID;
    private String projectName;
    private int projectView;
    private int projectLike;
    private boolean projectState;
    private boolean projectMotive;
    private boolean projectSub;
    private String projectTotal; // List로 변경
    private List<String> projectPlatform; // List로 변경
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

    public DetailedDto(Project project){
        this.userID = project.getUserID();
        this.projectName =  project.getProjectName();
        this.projectView =  project.getProjectView();
        this.projectLike =  project.getProjectLike();
        this.projectState =  project.isProjectState();
        this.projectMotive =  project.isProjectMotive();
        this.projectSub =  project.isProjectSub();
        this.projectTotal =  project.getProjectTotal();
        this.projectPlatform =  convertList(project.getProjectPlatform());
        this.createDate =  project.getCreateDate();
        this.projectImg =  project.getProjectImg();
        this.projectField =  project.getProjectField();
        this.projectArea =  project.getProjectArea();
        this.projectStart =  project.getProjectStart();
        this.projectEnd =  project.getProjectEnd();
        this.projectContent =  project.getProjectContent();
        this.projectOnOff =  project.getProjectOnOff();
        this.projectReference =  project.getProjectReference();
        this.projectSkill =  project.getProjectSkill();
    }

    public List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    }

}
