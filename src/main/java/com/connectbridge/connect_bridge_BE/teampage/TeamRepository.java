package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface TeamRepository extends JpaRepository<RegisterEntity, Long> {
    RegisterEntity findByid(Long id);

}
