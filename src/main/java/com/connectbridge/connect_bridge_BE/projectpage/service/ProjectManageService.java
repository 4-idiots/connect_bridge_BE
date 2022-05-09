package com.connectbridge.connect_bridge_BE.projectpage.service;

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

    public ProjectManageService(SubmitRepository submitRepository, ProjectRepository projectRepository) {
        this.submitRepository = submitRepository;
        this.projectRepository = projectRepository;
    }

    // 신청은 했지만 승인되지 않은 유저의 submit 정보 전달.
    public List<HashMap<String, Object>> findSubInfo(Long projectID) {
        List<SubmitMapping> submitEntityList = submitRepository.findByprojectIDAndAccept(projectID, false);
        List<HashMap<String, Object>> submitList = new ArrayList<>();

        for (int i = 0; i < submitEntityList.size(); i++) {

            HashMap<String, Object> subInfo = new HashMap<>();

            subInfo.put("field", submitEntityList.get(i).getField());
            subInfo.put("submitID", submitEntityList.get(i).getid());
            subInfo.put("userID", submitEntityList.get(i).getUserID());

            submitList.add(i, subInfo);
        }

        return submitList;
    }

    public boolean manageSub(Long projectID, Long submitID, Long userID, boolean input) {
        ProjectEntity projectEntity = projectRepository.findByid(projectID);

        // input apply = true, input reject = false
        //project leader가 token user와 같은가?
        System.out.println("확인4");

        if (userID.equals(projectEntity.getUserID())) {
            System.out.println("확인5");

            SubmitEntity submitEntity = submitRepository.findByid(submitID);

            // true => apply
            if(input){

                submitEntity.updateAccept(true);
                System.out.println("확인6");

                submitRepository.save(submitEntity);
                System.out.println("확인7");

                return true;
            }
            // false => reject
            submitRepository.deleteById(submitID);
            System.out.println("확인8");

            return false;
        }
        System.out.println("리더가 아닌 유저임.");

        return false;
    }

}
