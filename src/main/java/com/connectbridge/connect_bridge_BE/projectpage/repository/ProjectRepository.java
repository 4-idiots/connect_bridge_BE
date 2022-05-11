package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.ProjectDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    ProjectEntity findByid(Long id);
    ProjectEntity findByUserID(Long userID);
    List<ProjectEntity> findTop4ByOrderByIdDesc(); // 역순 4개 전달.
    //MemberMapping findByID(Long id); // noticeService
}