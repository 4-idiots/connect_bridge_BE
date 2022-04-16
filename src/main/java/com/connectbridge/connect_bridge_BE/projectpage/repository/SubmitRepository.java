package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitRepository extends JpaRepository<SubmitEntity, Long> {
    Boolean existsByUserIDAndProjectID(Long userID, Long projectID);
    SubmitEntity findByUserID(Long userID);
}
