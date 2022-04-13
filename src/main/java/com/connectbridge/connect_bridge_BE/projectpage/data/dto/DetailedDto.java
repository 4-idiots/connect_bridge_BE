package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    private List projectContent;
    private boolean projectOnOff;
    private String projectReference;
    private String projectSkill;

    public DetailedDto(Project project) {
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
        this.projectContent = jacksonMap(project.getProjectContent());
        this.projectOnOff =  project.isProjectOnOff();
        this.projectReference =  project.getProjectReference();
        this.projectSkill =  project.getProjectSkill();
    }
/*
    //이게 맞는지는 모르겠음.
    public List jacksonMap(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //List<Map<Object, Object>> map = mapper.readValue(json, List.class);
        List map = mapper.readValue(json, List.class);

        System.out.println(map);
        return map;
    }

 */

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


    public List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    }

}
