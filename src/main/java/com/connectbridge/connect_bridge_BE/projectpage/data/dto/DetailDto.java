package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class DetailDto {

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


    String projectName;
    String projectImg;
    List content;
    String projectField;
    boolean projectOnOff;
    String projectArea;
    List projectTotal;
    String projectReference;
    String projectStart;
    String projectEnd;
    List<String> projectPlatform;
    String projectSkill;
    int projectView;
    int projectLike;
    Long userID; // 4.18 추가

    List memberID; // 지원한 유저들정보
    HashMap leaderInfo;
    List memberList;
    boolean projectSub;

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

    public DetailDto(ProjectEntity projectEntity){
         this.uiuxPlan=projectEntity.getUiuxPlan();
         this.gamePlan = projectEntity.getGamePlan();
         this.managerPlan = projectEntity.getManagerPlan();
         this.hwPlan = projectEntity.getHwPlan();
         this.iosFr = projectEntity.getIosFr();
         this.androidFr = projectEntity.getAndroidFr();
         this.webFrontFr = projectEntity.getWebFrontFr();
         this.webPublicFr = projectEntity.getWebPublicFr();
         this.crossFr = projectEntity.getCrossFr();
         this.uiuxDe = projectEntity.getUiuxDe();
         this.graphicDe = projectEntity.getGraphicDe();
         this.thrdDe = projectEntity.getThrdDe();
         this.hwDe = projectEntity.getHwDe();
         this.etcDe = projectEntity.getEtcDe();
         this.webBk = projectEntity.getWebBk();
         this.blchBk = projectEntity.getBlchBk();
         this.aiBk = projectEntity.getAiBk();
         this.dsBk = projectEntity.getDsBk();
         this.gameBk = projectEntity.getGameBk();
         this.planBu = projectEntity.getPlanBu();
         this.marketingBu = projectEntity.getMarketingBu();
         this.financeBu = projectEntity.getFinanceBu();
         this.salesBu = projectEntity.getSalesBu();
         this.consultBu = projectEntity.getSalesBu();
         this.investBu = projectEntity.getInvestBu();
         this.etcBu = projectEntity.getEtcBu();
         this.blogEtc = projectEntity.getBlogEtc();
         this.influEtc = projectEntity.getInfluEtc();
         this.compEtc = projectEntity.getCompEtc();
         this.uiuxPlanNow = projectEntity.getUiuxPlanNow();
         this.gamePlanNow = projectEntity.getGamePlanNow();
         this.managerPlanNow = projectEntity.getManagerPlanNow();
         this.hwPlanNow = projectEntity.getHwPlanNow();
         this.iosFrNow = projectEntity.getIosFrNow();
         this.androidFrNow = projectEntity.getAndroidFrNow();
         this.webFrontFrNow = projectEntity.getWebFrontFrNow();
         this.webPublicFrNow = projectEntity.getWebPublicFrNow();
         this.crossFrNow = projectEntity.getCrossFrNow();
         this.uiuxDeNow = projectEntity.getUiuxDeNow();
         this.graphicDeNow = projectEntity.getGraphicDeNow();
         this.thrdDeNow = projectEntity.getThrdDeNow();
         this.hwDeNow = projectEntity.getHwDeNow();
         this.etcDeNow = projectEntity.getEtcDeNow();
         this.webBkNow = projectEntity.getWebBkNow();
         this.blchBkNow = projectEntity.getBlchBkNow();
         this.aiBkNow = projectEntity.getAiBkNow();
         this.dsBkNow = projectEntity.getDsBkNow();
         this.gameBkNow = projectEntity.getGameBkNow();
         this.planBuNow = projectEntity.getPlanBuNow();
         this.marketingBuNow = projectEntity.getMarketingBuNow();
         this.financeBuNow = projectEntity.getFinanceBuNow();
         this.salesBuNow = projectEntity.getSalesBuNow();
         this.consultBuNow = projectEntity.getConsultBuNow();
         this.etcBuNow = projectEntity.getEtcBuNow();
         this.blogEtcNow = projectEntity.getBlogEtcNow();
         this.influEtcNow = projectEntity.getInfluEtcNow();
         this.compEtcNow = projectEntity.getCompEtcNow();
         this.projectName = projectEntity.getProjectName();
         this.projectImg = projectEntity.getProjectImg();
         this.content = jacksonMap(projectEntity.getContent());
         this.projectField = projectEntity.getProjectField();
         this.projectOnOff =projectEntity.isProjectOnOff();
         this.projectArea = projectEntity.getProjectArea();
         this.projectTotal = jacksonMap(projectEntity.getProjectTotal());
         this.projectReference = projectEntity.getProjectReference();
         this.projectStart = projectEntity.getProjectStart();
         this.projectEnd = projectEntity.getProjectEnd();
         this.projectPlatform = convertList(projectEntity.getProjectPlatform());
         this.projectSkill = projectEntity.getProjectSkill();
         this.projectLike =projectEntity.getProjectLike();
         this.projectView = projectEntity.getProjectView();
         this.userID = projectEntity.getUserID();
    }

}
