
package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateReqDto {

    private Long projectID;

    private String projectName;

    private boolean projectMotive;

    private String projectImg;

    private String projectContent;

    private String projectField;

    private String projectOnOff;

    private String projectArea;

    private String projectTotal;

    private String projectReference;

    private String projectPlatform;

    private String projectSkill;

    private String projectStart;

    private String projectEnd;

    private Long userID;

    public Project CreateReqDto() {
        return Project.builder()
                .projectID(projectID)
                .projectName(projectName)
                .projectMotive(projectMotive)
                .projectImg(projectImg)
                .projectContent(projectContent)
                .projectField(projectField)
                .projectOnOff(projectOnOff)
                .projectArea(projectArea)
                .projectTotal(projectTotal)
                .projectReference(projectReference)
                .projectPlatform(projectPlatform)
                .projectSkill(projectSkill)
                .projectStart(projectStart)
                .projectEnd(projectEnd)
                .userID(userID)
                .build();
    }
}