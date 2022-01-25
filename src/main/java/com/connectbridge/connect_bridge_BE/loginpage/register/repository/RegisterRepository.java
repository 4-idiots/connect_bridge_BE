package com.connectbridge.connect_bridge_BE.loginpage.register.repository;


import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<RegisterEntity, Long>{
}
