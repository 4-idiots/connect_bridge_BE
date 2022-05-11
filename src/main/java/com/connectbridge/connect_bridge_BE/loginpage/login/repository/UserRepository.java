package com.connectbridge.connect_bridge_BE.loginpage.login.repository;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    LeaderMapping findByid(Long id);
    List<User> getByid(Long id); // 5.11 생성.
    User findByUserID(String userID);
    User findByUserEmail(String userEmail);
    User findByIdAndUserIDAndAndUserNameAndRole(Long id, String userID, String userName, boolean role);
}