package com.connectbridge.connect_bridge_BE.loginpage.login.service;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
       this.userRepository= userRepository;
    }

    public boolean login(String userID, String userPW){
        Optional<User> user = userRepository.findByUserID(userID);
        log.info("db password = {}, input password ={}", user.get().getUserPW(),userPW);
        if(user.get().getUserPW().equals(userPW)){
            return true;
        }
        return false;
    }

}
