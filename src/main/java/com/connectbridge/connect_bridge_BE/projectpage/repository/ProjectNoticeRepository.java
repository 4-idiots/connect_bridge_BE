package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectNoticeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectNoticeRepository extends JpaRepository<ProjectNoticeEntity, Long> {
    List<ProjectNoticeEntity> findByProjectID(Long projectID, Sort id);
}
