package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitRepository extends JpaRepository<SubmitEntity, Long> {
    Boolean existsByUserIDAndProjectID(Long userID, Long projectID);
    SubmitEntity findByUserID(Long userID);
    List<MemberMapping> findByProjectIDAndAccept(Long userID, boolean accept);
    SubmitEntity findByid(Long id);
    List<SubmitMapping> findByprojectIDAndAccept(Long projectID,boolean accept);
}
