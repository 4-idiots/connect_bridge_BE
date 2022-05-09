package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectLikeRepository extends JpaRepository<ProjectLikeEntity, Long> {
    ProjectLikeEntity findByUserIDAndProjectID(Long userID, Long projectID);
    Long countByProjectID(Long projectID);
    boolean existsByUserIDAndProjectID(Long userID, Long projectID);
}
