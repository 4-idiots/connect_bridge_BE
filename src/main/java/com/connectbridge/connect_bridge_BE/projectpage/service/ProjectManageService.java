package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.SubmitMapping;
import com.connectbridge.connect_bridge_BE.projectpage.repository.SubmitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProjectManageService {

    private final SubmitRepository submitRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectManageService(SubmitRepository submitRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.submitRepository = submitRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    // 신청은 했지만 승인되지 않은 유저의 submit 정보 전달.
    public List<HashMap<String, Object>> findSubInfo(Long projectID) {
        List<SubmitMapping> submitEntityList = submitRepository.findByprojectIDAndAccept(projectID, false);
        List<HashMap<String, Object>> submitList = new ArrayList<>();
        //submit Entity에 있는

        for (int i = 0; i < submitEntityList.size(); i++) {

            HashMap<String, Object> subInfo = new HashMap<>();

            subInfo.put("field", submitEntityList.get(i).getField());
            subInfo.put("submitID", submitEntityList.get(i).getid());
            subInfo.put("userID", submitEntityList.get(i).getUserID());
            //user에 있는 정보 호출,introduce,nickname,img,ability,interestSub
            List<User> user = userRepository.getByid(submitEntityList.get(i).getUserID());
            for(int j = 0; j< user.size();j++){
                subInfo.put("introduce",user.get(j).getIntroduce());
                subInfo.put("nickname",user.get(j).getUserNickName());
                subInfo.put("img",user.get(j).getPicture());
                subInfo.put("ability",user.get(j).getUserAbility());
                subInfo.put("interestSub",user.get(j).getUserInterestSub());
            }
            submitList.add(i, subInfo);
        }

        return submitList;
    }

    public boolean manageSub(Long projectID, Long submitID, Long userID, boolean input) {
        ProjectEntity projectEntity = projectRepository.findByid(projectID);

        // input apply = true, input reject = false
        //project leader가 token user와 같은가?
        if (userID.equals(projectEntity.getUserID())) {
            System.out.println("in the if userID: "+userID+" proUID: "+projectEntity.getUserID());
            System.out.println("이 사람은 리더가 맞습니다.");

            SubmitEntity submitEntity = submitRepository.findByid(submitID);
            // true => apply
            if(input){

                String target = submitEntity.getField();
                System.out.println("Field: "+ target);
                controll(target,projectEntity);
                submitEntity.updateAccept(true);
                submitRepository.save(submitEntity);

                return true;
            }
            // false => reject
            submitRepository.deleteById(submitID);

            return false;
        }
        System.out.println("리더가 아닌 유저임.");

        return false;
    }

    private void controll(String target,ProjectEntity projectEntity){
        int now =0;
        int max= 0;
        switch (target){
            case "uiux_plan":
                now = projectEntity.getUiuxPlanNow();
                max = projectEntity.getUiuxPlan();

                if(now<max){
                    now += 1;
                    projectEntity.setUiuxPlanNow(now);
                    projectRepository.save(projectEntity);
                    System.out.println("1");
                }
                break;
            case "game_plan":
                now = projectEntity.getGamePlanNow();
                max = projectEntity.getGamePlan();

                if(now<max){
                    now += 1;
                    projectEntity.setGamePlanNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "manager_plan":
                now = projectEntity.getManagerPlanNow();
                max = projectEntity.getManagerPlan();

                if(now<max){
                    now += 1;
                    projectEntity.setManagerPlanNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "hw_plan":
                now = projectEntity.getHwPlanNow();
                max = projectEntity.getHwPlan();

                if(now<max){
                    now += 1;
                    projectEntity.setHwPlanNow(now);
                    projectRepository.save(projectEntity);
                    System.out.println("2");
                }
                break;
            case "ios_fr":
                now = projectEntity.getIosFrNow();
                max = projectEntity.getIosFr();

                if(now<max){
                    now += 1;
                    projectEntity.setIosFrNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "android_fr":
                now = projectEntity.getAndroidFrNow();
                max = projectEntity.getAndroidFr();

                if(now<max){
                    now += 1;
                    projectEntity.setAndroidFrNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "webFront_fr":
                now = projectEntity.getWebFrontFrNow();
                max = projectEntity.getWebFrontFr();

                if(now<max){
                    now += 1;
                    projectEntity.setWebFrontFrNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "webPublic_fr":
                 now = projectEntity.getWebPublicFrNow();
                 max = projectEntity.getWebPublicFr();

                if(now<max){
                    now += 1;
                    projectEntity.setWebPublicFrNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "cross_fr":
                 now = projectEntity.getCrossFrNow();
                 max = projectEntity.getCrossFr();

                if(now<max){
                    now += 1;
                    projectEntity.setCrossFrNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "uiux_de":
                 now = projectEntity.getUiuxDeNow();
                 max = projectEntity.getUiuxDe();

                if(now<max){
                    now += 1;
                    projectEntity.setUiuxDeNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "graphic_de":
                 now = projectEntity.getGraphicDeNow();
                 max = projectEntity.getGraphicDe();

                if(now<max){
                    now += 1;
                    projectEntity.setGraphicDeNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "thrd_de":
                 now = projectEntity.getThrdDeNow();
                 max = projectEntity.getThrdDe();

                if(now<max){
                    now += 1;
                    projectEntity.setThrdDeNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "hw_de":
                 now = projectEntity.getHwDeNow();
                 max = projectEntity.getHwDe();

                if(now<max){
                    now += 1;
                    projectEntity.setHwDeNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "etc_de":
                 now = projectEntity.getEtcDeNow();
                 max = projectEntity.getEtcDe();

                if(now<max){
                    now += 1;
                    projectEntity.setEtcDeNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "web_bk":
                 now = projectEntity.getWebBkNow();
                 max = projectEntity.getWebBk();

                if(now<max){
                    now += 1;
                    projectEntity.setWebBkNow(now);
                    projectRepository.save(projectEntity);
                    System.out.println("3");

                }
                break;
            case "blch_bk":
                 now = projectEntity.getBlchBkNow();
                 max = projectEntity.getBlchBk();

                if(now<max){
                    now += 1;
                    projectEntity.setBlchBkNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "ai_bk":
                 now = projectEntity.getAiBkNow();
                 max = projectEntity.getAiBk();

                if(now<max){
                    now += 1;
                    projectEntity.setAiBkNow(now);
                    projectRepository.save(projectEntity);
                    System.out.println("4");

                }
                break;
            case "ds_bk":
                 now = projectEntity.getDsBkNow();
                 max = projectEntity.getDsBk();

                if(now<max){
                    now += 1;
                    projectEntity.setDsBkNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "game_bk":
                 now = projectEntity.getGameBkNow();
                 max = projectEntity.getGameBk();

                if(now<max){
                    now += 1;
                    projectEntity.setGameBkNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "plan_bu":
                 now = projectEntity.getPlanBuNow();
                 max = projectEntity.getPlanBu();

                if(now<max){
                    now += 1;
                    projectEntity.setPlanBuNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "marketing_bu":
                 now = projectEntity.getMarketingBuNow();
                 max = projectEntity.getMarketingBu();

                if(now<max){
                    now += 1;
                    projectEntity.setMarketingBuNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "finance_bu":
                 now = projectEntity.getFinanceBuNow();
                 max = projectEntity.getFinanceBu();

                if(now<max){
                    now += 1;
                    projectEntity.setFinanceBuNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "sales_bu":
                 now = projectEntity.getSalesBuNow();
                 max = projectEntity.getSalesBu();

                if(now<max){
                    now += 1;
                    projectEntity.setSalesBuNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "consult_bu":
                 now = projectEntity.getConsultBuNow();
                 max = projectEntity.getConsultBu();

                if(now<max){
                    now += 1;
                    projectEntity.setConsultBuNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "invest_bu":
                 now = projectEntity.getInvestBuNow();
                 max = projectEntity.getInvestBu();

                if(now<max){
                    now += 1;
                    projectEntity.setInvestBuNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "etc_bu":
                 now = projectEntity.getEtcBuNow();
                 max = projectEntity.getEtcBu();

                if(now<max){
                    now += 1;
                    projectEntity.setEtcBuNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "blog_etc":
                 now = projectEntity.getBlogEtcNow();
                 max = projectEntity.getBlogEtc();

                if(now<max){
                    now += 1;
                    projectEntity.setBlogEtcNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "influ_etc":
                 now = projectEntity.getInfluEtcNow();
                 max = projectEntity.getInfluEtc();

                if(now<max){
                    now += 1;
                    projectEntity.setInfluEtcNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
            case "comp_etc":
                 now = projectEntity.getCompEtc();
                 max = projectEntity.getCompEtc();

                if(now<max){
                    now += 1;
                    projectEntity.setCompEtcNow(now);
                    projectRepository.save(projectEntity);
                }
                break;
        }
    }


}
