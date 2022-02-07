package com.connectbridge.connect_bridge_BE.loginpage.login.repository;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserID(String userID);
}
