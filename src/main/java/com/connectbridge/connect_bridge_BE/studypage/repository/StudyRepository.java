package com.connectbridge.connect_bridge_BE.studypage.repository;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyRepository extends JpaRepository<StudyEntity, Long> {

    List<StudyEntity> findTop4ByOrderByIdDesc(); // id 큰 순으로 4개 select
    StudyEntity findByid(Long studyID);
}
