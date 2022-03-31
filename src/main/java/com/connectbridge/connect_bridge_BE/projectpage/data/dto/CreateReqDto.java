
package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


// 3.26 수정된 api로 변경됨.
@Getter
@Setter
public class CreateReqDto {

    private Long userID;

    private boolean projectMotive;

    private String projectName;

    private String projectImg;

    private String projectContent;

    private String projectField;

    private String projectOnOff;

    private String projectArea;

    private String projectReference;

    private String projectSkill;

    private String projectStart;

    private String projectEnd;

    private String projectTotal; // List
    private String projectPlatform; // List



    public Project Create() {
        return Project.builder()
                .userID(userID)
                .projectName(projectName)
                .projectMotive(projectMotive)
                .projectImg(projectImg)
                .projectContent(projectContent)
                .projectField(projectField)
                .projectOnOff(projectOnOff)
                .projectArea(projectArea)
                .projectTotal(projectTotal)
                .projectReference(projectReference)
                .projectStart(projectStart)
                .projectEnd(projectEnd)
                .projectPlatform(projectPlatform)
                .projectSkill(projectSkill)
                .build();
    }
}