package com.connectbridge.connect_bridge_BE.loginpage.register.repository;


import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<RegisterEntity, Long>{
    RegisterEntity findByid(long id);
    RegisterEntity getTop1ByUserNickname(String userNickname);


    Boolean existsByuserNickname(String userNickname);
    Boolean existsByuserID(String userID);
    Boolean existsByuserEmail(String userEmail);
}