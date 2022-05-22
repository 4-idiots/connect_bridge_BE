package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.mypage.IdMapping;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitRepository extends JpaRepository<SubmitEntity, Long> {
    Boolean existsByUserIDAndProjectID(Long userID, Long projectID);

    List<MemberMapping> findByProjectIDAndAccept(Long projectID, boolean accept);
    List<SubmitMapping> findByprojectIDAndAccept(Long projectID,boolean accept);
    List<SubmitEntity> findByUserIDAndAccept(Long userID, boolean accept);
    SubmitEntity findByid(Long id);
    SubmitEntity findByProjectIDAndUserIDAndAccept(Long projectID,Long memberID, boolean accept);
    SubmitEntity findByUserIDAndProjectID(Long userID, Long projectID);

    IdMapping findByProjectIDAndUserID(Long projectID, Long userID); //project에 submitID줄때 필요함.
}
