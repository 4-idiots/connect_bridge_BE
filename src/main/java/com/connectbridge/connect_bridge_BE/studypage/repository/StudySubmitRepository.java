package com.connectbridge.connect_bridge_BE.studypage.repository;

import com.connectbridge.connect_bridge_BE.mypage.IdMapping;
import com.connectbridge.connect_bridge_BE.projectpage.repository.MemberMapping;
import com.connectbridge.connect_bridge_BE.projectpage.repository.SubmitMapping;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudySubmitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudySubmitRepository extends JpaRepository<StudySubmitEntity, Long> {
    Boolean existsByUserIDAndStudyID(Long userID, Long projectID);
    List<SubmitMapping> findBystudyIDAndAccept(Long studyID, boolean accept);
    List<MemberMapping> findByStudyIDAndAccept(Long studyID, boolean accept);
    StudySubmitEntity findByStudyIDAndUserIDAndAccept(Long studyID, Long memberID, boolean accept);
    StudySubmitEntity findByUserIDAndStudyID(Long userID, Long studyID);
    StudySubmitEntity findByid(Long submitID);

    IdMapping findByStudyIDAndUserID(Long studyID, Long userID);// submitID 추가에 필요함.

    List<StudySubmitEntity> findByUserIDAndAccept(Long userID, boolean accept);
}
