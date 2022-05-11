package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectLikeRepository extends JpaRepository<ProjectLikeEntity, Long> {
    ProjectLikeEntity findByUserIDAndProjectID(Long userID, Long projectID);
    Long countByProjectID(Long projectID);
    boolean existsByUserIDAndProjectID(Long userID, Long projectID);

    //List<ProjectEntity> findProjectIDByUserID(long fromUserId);
}
