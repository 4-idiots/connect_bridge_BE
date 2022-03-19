package com.connectbridge.connect_bridge_BE.outactpage.repository;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutAct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutActRepository extends JpaRepository<OutAct, Long> {

    OutAct findByid(Long id);

    void deleteById(Long id);
}
