package com.connectbridge.connect_bridge_BE.community;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
    CommunityEntity findByid(Long id);

    List<CommunityEntity> searchResultByTitleContaining(String keyword);

}
