package com.connectbridge.connect_bridge_BE.studypage.repository;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyNoticeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyNoticeRepository extends JpaRepository<StudyNoticeEntity,Long> {
    List<StudyNoticeEntity> findByStudyID(Long studyID, Sort id);

}
