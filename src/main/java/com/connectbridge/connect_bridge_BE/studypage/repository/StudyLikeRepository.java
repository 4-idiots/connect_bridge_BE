package com.connectbridge.connect_bridge_BE.studypage.repository;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyLikeRepository extends JpaRepository<StudyLikeEntity, Long> {

    StudyLikeEntity findByUserIDAndStudyID(Long userID, Long studyID);
    Long countByStudyID(Long studyID);
    boolean existsByUserIDAndStudyID(Long userID, Long studyID);
}
