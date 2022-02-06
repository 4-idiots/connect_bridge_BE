package com.connectbridge.connect_bridge_BE.loginpage.login.repository;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    LoginEntity findByUserIDAndUserPW(String userID,String userPW);

}
