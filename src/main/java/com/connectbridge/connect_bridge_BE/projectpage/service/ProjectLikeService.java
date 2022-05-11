package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutActLike;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectLikeEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectLikeRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectLikeService {

    private final ProjectLikeRepository projectLikeRepository;
    private final ProjectRepository projectRepository;

    public ProjectLikeService(ProjectLikeRepository projectLikeRepository, ProjectRepository projectRepository) {
        this.projectLikeRepository = projectLikeRepository;
        this.projectRepository = projectRepository;
    }

    public boolean likeChk(Long userID, Long projectID) {
        if (null == projectLikeRepository.findByUserIDAndProjectID(userID, projectID)) {
            return true;
        } else {
            return false;
        }
    }

    public void likeOff(Long userID, Long projectID){
        ProjectLikeEntity projectLike = projectLikeRepository.findByUserIDAndProjectID(userID, projectID);
        projectLikeRepository.deleteById(projectLike.getId());
        likeCount(projectID);

    }

    public void likeOn(Long userID, Long projectID){
        ProjectLikeEntity projectLike = new ProjectLikeEntity().createProjectLike(userID,projectID);
        projectLikeRepository.save(projectLike);
        likeCount(projectID);

    }

    private void likeCount(Long projectID){
        long like = projectLikeRepository.countByProjectID(projectID);
        ProjectEntity project = projectRepository.findByid(projectID);
        project.updateLike((int)like);
        projectRepository.save(project);
    }

    public boolean isLike(Long userID, Long projectID){
        return projectLikeRepository.existsByUserIDAndProjectID(userID,projectID);
    }
}

