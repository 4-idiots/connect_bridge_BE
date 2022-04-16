package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateDto {
    private Long userID;
    private String projectName;
    private boolean projectMotive;
    private String projectStrImg;
    private String projectContent;
    private String projectField;
    private String projectArea;
    private String projectTotal;
    private String projectReference;
    private String projectStart;
    private String projectEnd;
    private List<String> projectPlatform;
    private String projectSkill;

    private int uiuxPlan;
    private int gamePlan;
    private int managerPlan;
    private int hwPlan;

    private int iosFr;
    private int androidFr;
    private int webFrontFr;
    private int webPublicFr;
    private int crossFr;

    private int uiuxDe;
    private int graphicDe;
    private int thrdDe;
    private int hwDe;
    private int etcDe;

    private int webBk;
    private int blchBk;
    private int aiBk;
    private int dsBk;
    private int gameBk;

    private int planBu;
    private int marketingBu;
    private int financeBu;
    private int salesBu;
    private int consultBu;
    private int investBu;
    private int etcBu;

    private int blogEtc;
    private int influEtc;
    private int compEtc;

    private int uiuxPlanNow;
    private int gamePlanNow;
    private int managerPlanNow;
    private int hwPlanNow;

    private int iosFrNow;
    private int androidFrNow;
    private int webFrontFrNow;
    private int webPublicFrNow;
    private int crossFrNow;

    private int uiuxDeNow;
    private int graphicDeNow;
    private int thrdDeNow;
    private int hwDeNow;
    private int etcDeNow;

    private int webBkNow;
    private int blchBkNow;
    private int aiBkNow;
    private int dsBkNow;
    private int gameBkNow;

    private int planBuNow;
    private int marketingBuNow;
    private int financeBuNow;
    private int salesBuNow;
    private int consultBuNow;
    private int investBuNow;
    private int etcBuNow;

    private int blogEtcNow;
    private int influEtcNow;
    private int compEtcNow;
}
