package com.connectbridge.connect_bridge_BE.studypage.repository;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudySubmitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudySubmitRepository extends JpaRepository<StudySubmitEntity, Long> {
    Boolean existsByUserIDAndStudyID(Long userID, Long projectID);

}
