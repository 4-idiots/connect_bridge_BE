
package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// 3.26 수정된 api로 변경됨.
@Getter
@Setter
public class CreateDto {

    private Long userID;

    private String projectName;

    private boolean projectMotive;

    private MultipartFile projectImg;

    private String projectContent;

    private String projectField;

    //private String projectOnOff;

    private String projectArea;

    private String projectReference;

    private String projectSkill;

    private String projectStart;

    private String projectEnd;

    private String projectTotal; // List

    private List<String> projectPlatform; // List

    /*
     Postman에서 넣으려면 JSON parse error가 발생한다.
     희재가 통신으로 넣으면 그냥 들어가진다고 함.
     왜 되는지 잘 모르겠음.
     */
    @Builder
    public Project Create() {
        return Project.builder()
                .userID(userID)
                .projectName(projectName)
                .projectMotive(projectMotive)
                .projectImg(projectImg)
                .projectContent(projectContent)
                .projectField(projectField)
                //.projectOnOff(projectOnOff)
                .projectArea(projectArea)
                .projectTotal(projectTotal)
                .projectReference(projectReference)
                .projectStart(projectStart)
                .projectEnd(projectEnd)
                .projectPlatform(listToStr(projectPlatform))
                .projectSkill(projectSkill)
                .build();
    }
    public String listToStr(List<String> test) {
        String str = String.join(",", test);
        System.out.println(str);
        return str;
    }

}