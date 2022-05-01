package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.BaseTimeEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.CreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@DynamicUpdate @Builder
@Table(name = "project")
public class ProjectEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userID; // user table의 user_id(pk)를 fk로 사용

    @Column(name = "project_name")
    private String projectName; // 프로젝트 이름

    @Column(name = "project_img")
    private String projectImg; // 프로젝트 사진

    @Column(name = "project_content")
    private String content; // 프로젝트 설명

    @Column(name = "project_field")
    private String projectField; // 프로젝트 분야

    @Column(name = "project_onoff")
    private boolean projectOnOff; // 온, 오프라인 가능 여부

    @Column(name = "project_area")
    private String projectArea; // 프로젝트 가능 지역

    @Column(name = "project_total")
    private String projectTotal; // 프로젝트 모집인원 List로 받아야함.

    @Column(name = "project_reference")
    private String projectReference; // 참고자료

    @Column(name = "project_start")
    private String projectStart; // 프로젝트 시작일

    @Column(name= "project_end")
    private String projectEnd; // 프로젝트 종료일

    @Column(name = "project_platform")
    private String projectPlatform; // 출시 플렛폼 List롤 받아야함.

    @Column(name = "project_skill")
    private String projectSkill; // 프로젝트 언어

    @Column(name ="project_like")
    private int projectLike;

    @Column(name = "project_view")
    private int projectView;

    @Column(name = "uiux_plan")
    private int uiuxPlan;
    @Column(name = "game_plan")
    private int gamePlan;
    @Column(name = "manager_plan")
    private int managerPlan;
    @Column(name = "hw_plan")
    private int hwPlan;

    @Column(name = "ios_fr")
    private int iosFr;
    @Column(name = "android_fr")
    private int androidFr;
    @Column(name = "webfront_fr")
    private int webFrontFr;
    @Column(name = "webpublic_fr")
    private int webPublicFr;
    @Column(name = "cross_fr")
    private int crossFr;

    @Column(name = "uiux_de")
    private int uiuxDe;
    @Column(name = "graphic_de")
    private int graphicDe;
    @Column(name = "thrd_de")
    private int thrdDe;
    @Column(name = "hw_de")
    private int hwDe;
    @Column(name = "etc_de")
    private int etcDe;

    @Column(name = "web_bk")
    private int webBk;
    @Column(name = "blch_bk")
    private int blchBk;
    @Column(name = "ai_bk")
    private int aiBk;
    @Column(name = "ds_bk")
    private int dsBk;
    @Column(name = "game_bk")
    private int gameBk;

    @Column(name = "plan_bu")
    private int planBu;
    @Column(name = "marketing_bu")
    private int marketingBu;
    @Column(name = "finance_bu")
    private int financeBu;
    @Column(name = "sales_bu")
    private int salesBu;
    @Column(name = "consult_bu")
    private int consultBu;
    @Column(name = "invest_bu")
    private int investBu;
    @Column(name = "etc_bu")
    private int etcBu;

    @Column(name = "blog_etc")
    private int blogEtc;

    @Column(name = "influ_etc")
    private int influEtc;

    @Column(name = "comp_etc")
    private int compEtc;

    @Column(name = "uiux_plan_now")
    private int uiuxPlanNow;
    @Column(name = "game_plan_now")
    private int gamePlanNow;
    @Column(name = "manager_plan_now")
    private int managerPlanNow;
    @Column(name = "hw_plan_now")
    private int hwPlanNow;

    @Column(name = "ios_fr_now")
    private int iosFrNow;
    @Column(name = "android_fr_now")
    private int androidFrNow;
    @Column(name = "webfront_fr_now")
    private int webFrontFrNow;
    @Column(name = "webpublic_fr_now")
    private int webPublicFrNow;
    @Column(name = "cross_fr_now")
    private int crossFrNow;

    @Column(name = "uiux_de_now")
    private int uiuxDeNow;
    @Column(name = "graphic_de_now")
    private int graphicDeNow;
    @Column(name = "thrd_de_now")
    private int thrdDeNow;
    @Column(name = "hw_de_now")
    private int hwDeNow;
    @Column(name = "etc_de_now")
    private int etcDeNow;

    @Column(name = "web_bk_now")
    private int webBkNow;
    @Column(name = "blch_bk_now")
    private int blchBkNow;
    @Column(name = "ai_bk_now")
    private int aiBkNow;
    @Column(name = "ds_bk_now")
    private int dsBkNow;
    @Column(name = "game_bk_now")
    private int gameBkNow;

    @Column(name = "plan_bu_now")
    private int planBuNow;
    @Column(name = "marketing_bu_now")
    private int marketingBuNow;
    @Column(name = "finance_bu_now")
    private int financeBuNow;
    @Column(name = "sales_bu_now")
    private int salesBuNow;
    @Column(name = "consult_bu_now")
    private int consultBuNow;
    @Column(name = "invest_bu_now")
    private int investBuNow;
    @Column(name = "etc_bu_now")
    private int etcBuNow;

    @Column(name = "blog_etc_now")
    private int blogEtcNow;
    @Column(name = "influ_etc_now")
    private int influEtcNow;
    @Column(name = "comp_etc_now")
    private int compEtcNow;

    public void proEntUpdate(CreateDto createDto) {
        this.projectName = createDto.getProjectName();
        this.projectImg = createDto.getProjectStrImg();
        this.content = createDto.getContent();
        this.projectField = createDto.getProjectField();
        this.projectArea = createDto.getProjectArea();
        this.projectTotal = createDto.getProjectTotal();
        this.projectReference = createDto.getProjectReference();
        this.projectStart = createDto.getProjectStart();
        this.projectEnd = createDto.getProjectEnd();
        this.projectPlatform = listToStr(createDto.getProjectPlatform());
        this.projectSkill = createDto.getProjectSkill();

        this.uiuxPlan = createDto.getUiuxPlan();
        this.gamePlan = createDto.getGamePlan();
        this.managerPlan = createDto.getManagerPlan();
        this.hwPlan = createDto.getHwPlan();
        this.iosFr = createDto.getIosFr();
        this.androidFr = createDto.getAndroidFr();
        this.webFrontFr = createDto.getWebFrontFr();
        this.webPublicFr = createDto.getWebPublicFr();
        this.crossFr = createDto.getCrossFr();
        this.uiuxDe = createDto.getUiuxDe();
        this.graphicDe = createDto.getGraphicDe();
        this.thrdDe = createDto.getThrdDe();
        this.hwDe = createDto.getHwDe();
        this.etcDe = createDto.getEtcDe();
        this.webBk = createDto.getWebBk();
        this.blchBk = createDto.getBlchBk();
        this.aiBk = createDto.getAiBk();
        this.dsBk = createDto.getDsBk();
        this.gameBk = createDto.getGameBk();
        this.planBu = createDto.getPlanBu();
        this.marketingBu = createDto.getMarketingBu();
        this.financeBu = createDto.getFinanceBu();
        this.salesBu = createDto.getSalesBu();
        this.consultBu = createDto.getConsultBu();
        this.investBu = createDto.getInvestBu();
        this.etcBu = createDto.getEtcBu();
        this.blogEtc = createDto.getBlogEtc();
        this.influEtc = createDto.getInfluEtc();
        this.compEtc = createDto.getCompEtc();
        this.uiuxPlanNow = createDto.getUiuxPlanNow();
        this.gamePlanNow = createDto.getGamePlanNow();
        this.managerPlanNow = createDto.getManagerPlanNow();
        this.hwPlanNow = createDto.getHwPlanNow();
        this.iosFrNow = createDto.getIosFrNow();
        this.androidFrNow = createDto.getAndroidFrNow();
        this.webFrontFrNow = createDto.getWebFrontFrNow();
        this.webPublicFrNow = createDto.getWebPublicFrNow();
        this.crossFrNow = createDto.getCrossFrNow();
        this.uiuxDeNow = createDto.getUiuxDeNow();
        this.graphicDeNow = createDto.getGraphicDeNow();
        this.thrdDeNow = createDto.getThrdDeNow();
        this.hwDeNow = createDto.getHwDeNow();
        this.etcDeNow = createDto.getEtcDeNow();
        this.webBkNow = createDto.getWebBkNow();
        this.blchBkNow = createDto.getBlchBkNow();
        this.aiBkNow = createDto.getAiBkNow();
        this.dsBkNow = createDto.getDsBkNow();
        this.gameBkNow = createDto.getGameBkNow();
        this.planBuNow = createDto.getPlanBuNow();
        this.marketingBuNow = createDto.getMarketingBuNow();
        this.financeBuNow = createDto.getFinanceBuNow();
        this.salesBuNow = createDto.getSalesBuNow();
        this.consultBuNow = createDto.getConsultBuNow();
        this.investBuNow = createDto.getInvestBuNow();
        this.etcBuNow = createDto.getEtcBuNow();
        this.blogEtcNow = createDto.getBlogEtcNow();
        this.influEtcNow = createDto.getInfluEtcNow();
        this.compEtcNow = createDto.getCompEtcNow();

    }
    public String listToStr(List<String> test) {
        String str = String.join(",", test);
        System.out.println(str);
        return str;
    }
    public ProjectEntity createProject(CreateDto createDto){
        return builder()
                .projectName(createDto.getProjectName())
                .userID(createDto.getUserID())
                .projectImg(createDto.getProjectStrImg())
                .content(createDto.getContent())
                .projectField(createDto.getProjectField())
                .projectArea(createDto.getProjectArea())
                .projectTotal(createDto.getProjectTotal())
                .projectReference(createDto.getProjectReference())
                .projectStart(createDto.getProjectStart())
                .projectEnd(createDto.getProjectEnd())
                .projectPlatform(listToStr(createDto.getProjectPlatform()))
                .projectSkill(createDto.getProjectSkill())

                .uiuxPlan(createDto.getUiuxPlan())
                .gamePlan(createDto.getGamePlan())
                .managerPlan(createDto.getManagerPlan())
                .hwPlan(createDto.getHwPlan())
                .iosFr(createDto.getIosFr())
                .androidFr(createDto.getAndroidFr())
                .webFrontFr(createDto.getWebFrontFr())
                .webPublicFr(createDto.getWebPublicFr())
                .crossFr(createDto.getCrossFr())
                .uiuxDe(createDto.getUiuxDe())
                .graphicDe(createDto.getGraphicDe())
                .thrdDe(createDto.getThrdDe())
                .hwDe(createDto.getHwDe())
                .etcDe(createDto.getEtcDe())
                .webBk(createDto.getWebBk())
                .blchBk(createDto.getBlchBk())
                .aiBk(createDto.getAiBk())
                .dsBk(createDto.getDsBk())
                .gameBk(createDto.getGameBk())
                .planBu(createDto.getPlanBu())
                .marketingBu(createDto.getMarketingBu())
                .financeBu(createDto.getFinanceBu())
                .salesBu(createDto.getSalesBu())
                .consultBu(createDto.getConsultBu())
                .investBu(createDto.getInvestBu())
                .etcBu(createDto.getEtcBu())
                .blogEtc(createDto.getBlogEtc())
                .influEtc(createDto.getInfluEtc())
                .compEtc(createDto.getCompEtc())

                .uiuxPlanNow(createDto.getUiuxPlanNow())
                .gamePlanNow(createDto.getGamePlanNow())
                .managerPlanNow(createDto.getManagerPlanNow())
                .hwPlanNow(createDto.getHwPlanNow())
                .iosFrNow(createDto.getIosFrNow())
                .androidFrNow(createDto.getAndroidFrNow())
                .webFrontFrNow(createDto.getWebFrontFrNow())
                .webPublicFrNow(createDto.getWebPublicFrNow())
                .crossFrNow(createDto.getCrossFrNow())
                .uiuxDeNow(createDto.getUiuxDeNow())
                .graphicDeNow(createDto.getGraphicDeNow())
                .thrdDeNow(createDto.getThrdDeNow())
                .hwDeNow(createDto.getHwDeNow())
                .etcDeNow(createDto.getEtcDeNow())
                .webBkNow(createDto.getWebBkNow())
                .blchBkNow(createDto.getBlchBkNow())
                .aiBkNow(createDto.getAiBkNow())
                .dsBkNow(createDto.getDsBkNow())
                .gameBkNow(createDto.getGameBkNow())
                .planBuNow(createDto.getPlanBuNow())
                .marketingBuNow(createDto.getMarketingBuNow())
                .financeBuNow(createDto.getFinanceBuNow())
                .salesBuNow(createDto.getSalesBuNow())
                .consultBuNow(createDto.getConsultBuNow())
                .investBuNow(createDto.getInvestBuNow())
                .etcBuNow(createDto.getEtcBuNow())
                .blogEtcNow(createDto.getBlogEtcNow())
                .influEtcNow(createDto.getInfluEtcNow())
                .compEtcNow(createDto.getCompEtcNow())
                .build();
    }

}
