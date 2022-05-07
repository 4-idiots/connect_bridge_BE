package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectFollowEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectFollowRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectFollowService {

    private final ProjectFollowRepository projectFollowRepository;

    public ProjectFollowService(ProjectFollowRepository projectFollowRepository) {
        this.projectFollowRepository = projectFollowRepository;
    }


    public boolean followChk(Long userID, Long projectID) {
        if (!projectFollowRepository.existsByUserIDAndProjectID(userID, projectID)) {
            System.out.println("구독 안누른거 확인됨");
            return true;
        } else {
            System.out.println("구독 누른거 확인됨");
            return false;
        }
    }

    public void followOff(Long userID, Long projectID){
        ProjectFollowEntity projectFollowEntity = projectFollowRepository.findByUserIDAndProjectID(userID, projectID);
        projectFollowRepository.deleteById(projectFollowEntity.getId());
        System.out.println("팔로우 취소되었습니다!");
    }

    public void followOn(Long userID, Long projectID){
        ProjectFollowEntity projectFollowEntity = new ProjectFollowEntity().createProjectFollow(userID,projectID);
        projectFollowRepository.save(projectFollowEntity);

        System.out.println("팔로우 하였습니다!");
    }
}
