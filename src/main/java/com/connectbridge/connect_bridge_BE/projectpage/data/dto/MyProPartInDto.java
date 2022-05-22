package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class MyProPartInDto {

    Long projectID;
    Long userID;
    Long submitID;
    String projectName;
    String projectImg;
    List content;
    String projectField;
    boolean projectOnOff;
    String projectArea;

    String projectTotal;
    String projectReference;
    String projectStart;
    String projectEnd;
    List<String> projectPlatform;
    String projectSkill;
    int projectLike;
    int projectView;
    String projectOnline;

    LocalDateTime createDate;

    int uiuxPlan;
    int gamePlan;
    int managerPlan;
    int hwPlan;

    int iosFr;
    int androidFr;
    int webFrontFr;
    int webPublicFr;
    int crossFr;

    int uiuxDe;
    int graphicDe;
    int thrdDe;
    int hwDe;
    int etcDe;

    int webBk;
    int blchBk;
    int aiBk;
    int dsBk;
    int gameBk;

    int planBu;
    int marketingBu;
    int financeBu;
    int salesBu;
    int consultBu;
    int investBu;
    int etcBu;

    int blogEtc;
    int influEtc;
    int compEtc;

    int uiuxPlanNow;
    int gamePlanNow;
    int managerPlanNow;
    int hwPlanNow;

    int iosFrNow;
    int androidFrNow;
    int webFrontFrNow;
    int webPublicFrNow;
    int crossFrNow;

    int uiuxDeNow;
    int graphicDeNow;
    int thrdDeNow;
    int hwDeNow;
    int etcDeNow;

    int webBkNow;
    int blchBkNow;
    int aiBkNow;
    int dsBkNow;
    int gameBkNow;

    int planBuNow;
    int marketingBuNow;
    int financeBuNow;
    int salesBuNow;
    int consultBuNow;
    int investBuNow;
    int etcBuNow;

    int blogEtcNow;
    int influEtcNow;
    int compEtcNow;


    public List<String> convertList(String str){
        return Arrays.asList((str.split(",")));
    }
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
    public MyProPartInDto(BigInteger projectID, BigInteger userID, String projectName, String projectImg, String content, String projectField, boolean projectOnOff, String projectArea, String projectTotal, String projectReference, String projectStart, String projectEnd, String projectPlatform, String projectSkill, int projectLike, int projectView, Timestamp createDate, int uiuxPlan, int gamePlan, int managerPlan, int hwPlan, int iosFr, int androidFr, int webFrontFr, int webPublicFr, int crossFr, int uiuxDe, int graphicDe, int thrdDe, int hwDe, int etcDe, int webBk, int blchBk, int aiBk, int dsBk, int gameBk, int planBu, int marketingBu, int financeBu, int salesBu, int consultBu, int investBu, int etcBu, int blogEtc, int influEtc, int compEtc, int uiuxPlanNow, int gamePlanNow, int managerPlanNow, int hwPlanNow, int iosFrNow, int androidFrNow, int webFrontFrNow, int webPublicFrNow, int crossFrNow, int uiuxDeNow, int graphicDeNow, int thrdDeNow, int hwDeNow, int etcDeNow, int webBkNow, int blchBkNow, int aiBkNow, int dsBkNow, int gameBkNow, int planBuNow, int marketingBuNow, int financeBuNow, int salesBuNow, int consultBuNow, int investBuNow, int etcBuNow, int blogEtcNow, int influEtcNow, int compEtcNow, String projectOnline) {
        this.projectID = projectID.longValue();
        this.userID = userID.longValue();
        this.projectOnline = projectOnline;
        this.projectName = projectName;
        this.projectImg = projectImg;
        this.content = jacksonMap(String.valueOf(content));
        this.projectField = projectField;
        this.projectOnOff = projectOnOff;
        this.projectArea = projectArea;
        this.projectTotal = projectTotal;
        this.projectReference = projectReference;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.projectPlatform = convertList(String.valueOf(projectPlatform));
        this.projectSkill = projectSkill;
        this.projectLike = projectLike;
        this.projectView = projectView;
        this.createDate = createDate.toLocalDateTime();
        this.uiuxPlan = uiuxPlan;
        this.gamePlan = gamePlan;
        this.managerPlan = managerPlan;
        this.hwPlan = hwPlan;
        this.iosFr = iosFr;
        this.androidFr = androidFr;
        this.webFrontFr = webFrontFr;
        this.webPublicFr = webPublicFr;
        this.crossFr = crossFr;
        this.uiuxDe = uiuxDe;
        this.graphicDe = graphicDe;
        this.thrdDe = thrdDe;
        this.hwDe = hwDe;
        this.etcDe = etcDe;
        this.webBk = webBk;
        this.blchBk = blchBk;
        this.aiBk = aiBk;
        this.dsBk = dsBk;
        this.gameBk = gameBk;
        this.planBu = planBu;
        this.marketingBu = marketingBu;
        this.financeBu = financeBu;
        this.salesBu = salesBu;
        this.consultBu = consultBu;
        this.investBu = investBu;
        this.etcBu = etcBu;
        this.blogEtc = blogEtc;
        this.influEtc = influEtc;
        this.compEtc = compEtc;
        this.uiuxPlanNow = uiuxPlanNow;
        this.gamePlanNow = gamePlanNow;
        this.managerPlanNow = managerPlanNow;
        this.hwPlanNow = hwPlanNow;
        this.iosFrNow = iosFrNow;
        this.androidFrNow = androidFrNow;
        this.webFrontFrNow = webFrontFrNow;
        this.webPublicFrNow = webPublicFrNow;
        this.crossFrNow = crossFrNow;
        this.uiuxDeNow = uiuxDeNow;
        this.graphicDeNow = graphicDeNow;
        this.thrdDeNow = thrdDeNow;
        this.hwDeNow = hwDeNow;
        this.etcDeNow = etcDeNow;
        this.webBkNow = webBkNow;
        this.blchBkNow = blchBkNow;
        this.aiBkNow = aiBkNow;
        this.dsBkNow = dsBkNow;
        this.gameBkNow = gameBkNow;
        this.planBuNow = planBuNow;
        this.marketingBuNow = marketingBuNow;
        this.financeBuNow = financeBuNow;
        this.salesBuNow = salesBuNow;
        this.consultBuNow = consultBuNow;
        this.investBuNow = investBuNow;
        this.etcBuNow = etcBuNow;
        this.blogEtcNow = blogEtcNow;
        this.influEtcNow = influEtcNow;
        this.compEtcNow = compEtcNow;
        this.projectOnline = projectOnline;
    }
    public MyProPartInDto(ProjectEntity projectEntity){
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
        this.projectTotal = projectEntity.getProjectTotal();
        this.projectReference = projectEntity.getProjectReference();
        this.projectStart = projectEntity.getProjectStart();
        this.projectEnd = projectEntity.getProjectEnd();
        this.projectPlatform = convertList(projectEntity.getProjectPlatform());
        this.projectSkill = projectEntity.getProjectSkill();
        this.projectLike =projectEntity.getProjectLike();
        this.projectView = projectEntity.getProjectView();
        this.projectID = projectEntity.getId();
        this.userID = projectEntity.getUserID();
        this.createDate = projectEntity.getCreateDate();
        this.projectOnline = projectEntity.getProjectOnline();
    }
}
