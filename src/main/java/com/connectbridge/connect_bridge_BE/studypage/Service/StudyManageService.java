package com.connectbridge.connect_bridge_BE.studypage.Service;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.SubmitMapping;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectManageService;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudySubmitEntity;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudySubmitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudyManageService {

    private final StudySubmitRepository submitRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;


    public StudyManageService(StudySubmitRepository submitRepository, StudyRepository studyRepository, UserRepository userRepository) {
        this.submitRepository = submitRepository;
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
    }

    // submited info before accept
    public List<HashMap<String, Object>> findSubInfo(Long studyID) {
        List<SubmitMapping> submitEntityList = submitRepository.findBystudyIDAndAccept(studyID, false);
        List<HashMap<String, Object>> submitList = new ArrayList<>();

        for (int i = 0; i < submitEntityList.size(); i++) {
            HashMap<String, Object> subInfo = new HashMap<>();

            subInfo.put("field", submitEntityList.get(i).getField());
            subInfo.put("submitID", submitEntityList.get(i).getid());
            subInfo.put("userID", submitEntityList.get(i).getUserID());
            //user에 있는 정보 호출,introduce,nickname,img,ability,interestSub
            List<User> user = userRepository.getByid(submitEntityList.get(i).getUserID());
            for (User value : user) {
                subInfo.put("introduce", value.getIntroduce());
                subInfo.put("nickname", value.getUserNickName());
                subInfo.put("img", value.getPicture());
                subInfo.put("ability", value.getUserAbility());
                subInfo.put("interestSub", value.getUserInterestSub());
            }
            submitList.add(i, subInfo);
        }
        return submitList;
    }

    // study complete
    public void endStudy(Long userID, Long studyID){
        StudyEntity study = studyRepository.findByidAndUserID(studyID,userID);
        boolean status = study.isStudyOnOff();
        System.out.println("status: "+ status);
        if(status){
            study.updateOnOff(false);
        }else{
        study.updateOnOff(true);
        }
        studyRepository.save(study);
    }

    // study submit manage
    public boolean manageSub(Long studyID, Long submitID, Long userID, boolean input){
        StudyEntity studyEntity = studyRepository.findByid(studyID);

        if(userID.equals(studyEntity.getUserID())){
            StudySubmitEntity submitEntity = submitRepository.findByid(submitID);
            if(input){
                int max = studyEntity.getStudyMember();
                int now = studyEntity.getStudyMemberNow();
                if(now<max){
                    now+=1;
                    studyEntity.updateMemNow(now);
                    studyRepository.save(studyEntity);
                }
                submitEntity.updateAccept(true);
                submitRepository.save(submitEntity);
                return true;
            }
            submitRepository.deleteById(submitID);
            return false;
        }
        return  false;
    }

    // fired Study member
    public boolean fireStudyMember(Long userID, Long studyID, Long memberID){
        StudyEntity study = studyRepository.findByid(studyID);

        if(userID.equals(study.getUserID())){
            StudySubmitEntity submit = submitRepository.findByStudyIDAndUserIDAndAccept(studyID,memberID, true);

            // 여기서 필드는 주제임.
            Long targetID = submit.getId();
            submitRepository.deleteById(targetID);
            int now = study.getStudyMemberNow()-1;
            study.updateMemNow(now);
            studyRepository.save(study);
            return true;
        }
        return false;
    }
}

