package com.connectbridge.connect_bridge_BE.loginpage.login.service;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.FindIdReq;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.FindIdRes;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.LoginReqDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserService(UserRepository userRepository, JwtProvider jwtProvider){
       this.userRepository= userRepository;
        this.jwtProvider = jwtProvider;
    }

    // 로그인
    public TokenDto login(LoginReqDto reqDto){
        Optional<User> user = userRepository.findByUserID(reqDto.getUserID());
        //String pw; 암호화된 비밀번호;
        log.info("dbPW : {}, dtoPW : {}",user.get().getUserPW(), reqDto.getUserPW());

        if(user.get().getUserPW().equals(reqDto.getUserPW())){
            Long id = user.get().getId();
            String uID = user.get().getUserID();
            String uName = user.get().getUserName();
            String jwt = jwtProvider.createToken(id,uID,uName);
            System.out.println("비밀번호 확인 완료! jwt 발급");
            return new TokenDto(jwt);
        }
        else{
            throw new RuntimeException("service.login is Failed");
        }
    }

    // Email을 기준으로 id찾음.
    public FindIdRes findID(FindIdReq findIdReq){
        Optional<User> user = userRepository.findByUserEmail(findIdReq.getUserEmail());
        String message="no",userID = "no";
        if(user.get().getUserPhone().equals(findIdReq.getUserPhone()) &&
                user.get().getUserName().equals(findIdReq.getUserName())){
            message = "ok";
            userID = user.get().getUserID();
            //return new FindIdRes(message,userID);
        }
            return new FindIdRes(message,userID);
    }

}
