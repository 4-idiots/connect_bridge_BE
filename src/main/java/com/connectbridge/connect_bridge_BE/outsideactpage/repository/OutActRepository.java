package com.connectbridge.connect_bridge_BE.outsideactpage.repository;

import com.connectbridge.connect_bridge_BE.outsideactpage.data.entity.OutAct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutActRepository extends JpaRepository<OutAct, Long> {

    //Optional<OutAct> findById(Long id);
    OutAct findByid(Long id);

    void deleteById(Long id);
}
