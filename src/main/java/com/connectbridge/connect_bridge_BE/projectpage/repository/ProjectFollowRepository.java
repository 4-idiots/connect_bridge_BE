package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectFollowRepository extends JpaRepository<ProjectFollowEntity, Long> {
    boolean existsByUserIDAndProjectID(Long userID, Long projectID);
    ProjectFollowEntity findByUserIDAndProjectID(Long userID, Long projectID);
}
