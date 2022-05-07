package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmitRepository extends JpaRepository<SubmitEntity, Long> {
    Boolean existsByUserIDAndProjectID(Long userID, Long projectID);
    SubmitEntity findByUserID(Long userID);
    List<MemberMapping> findByProjectIDAndAccept(Long userID, boolean accept);
}
