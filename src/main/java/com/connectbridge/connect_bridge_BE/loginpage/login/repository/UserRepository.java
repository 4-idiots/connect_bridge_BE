package com.connectbridge.connect_bridge_BE.loginpage.login.repository;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserID(String userID);
    User findByUserEmail(String userEmail);
    User findByIdAndUserIDAndAndUserNameAndRole(Long id, String userID, String userName, boolean role);

}