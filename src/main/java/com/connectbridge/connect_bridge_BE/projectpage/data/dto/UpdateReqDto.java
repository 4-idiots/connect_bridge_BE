package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateReqDto{
    private Long projectID;
    private Long userID;
    private boolean projectMotive;
    private String projectName;
    private String projectImg;
    private String projectContent;
    private String projectField;
    private boolean projectOnOff;
    private String projectArea;
    private String projectReference;
    private String projectStart;
    private String projectEnd;
    private String projectSkill;
    private List<String> projectPlatform; // List
    private String projectTotal; // List
}
