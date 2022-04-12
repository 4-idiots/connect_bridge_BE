package com.connectbridge.connect_bridge_BE.community;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
    CommunityEntity findByid(Long id);


}
