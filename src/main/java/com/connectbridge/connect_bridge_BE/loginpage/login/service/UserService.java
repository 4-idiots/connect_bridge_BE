package com.connectbridge.connect_bridge_BE.loginpage.login.service;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.*;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final SendEmailService sendEmailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtProvider jwtProvider, SendEmailService sendEmailService, PasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.jwtProvider = jwtProvider;
        this.sendEmailService = sendEmailService;
        this.passwordEncoder = passwordEncoder;
    }

    // 로그인
    public TokenResDto login(LoginReqDto reqDto){
        User user = userRepository.findByUserID(reqDto.getUserID());
        log.info("userID : {}, userPW : {} ", user.getUserID(),user.getUserPW());
       String pw = user.getUserPW();
       // user.getUserPW().equals(reqDto.getUserPW())
        if(passwordEncoder.matches(reqDto.getUserPW(),pw)){
            Long id = user.getId();
            String uID = user.getUserID();
            String uName = user.getUserName();

            String accessToken = jwtProvider.createAccessToken(id,uID,uName);
            String refreshToken = jwtProvider.createRefreshToken();

            user.updateToken(refreshToken); // refresh 토큰 발급.
            userRepository.save(user); // refresh 토큰 db 저장.

            System.out.println("비밀번호 확인 완료! jwt 발급");
            return new TokenResDto(accessToken);
        }
        return new TokenResDto(null);
    }

    // 아이디 찾기
    public FindIdRes findID(FindIdReq findIdReq){
        User user = userRepository.findByUserEmail(findIdReq.getUserEmail());
        String message="no",userID = "no";
        if(user.getUserName().equals(findIdReq.getUserName())){
            message = "ok";
            userID = user.getUserID();
            //return new FindIdRes(message,userID);
        }
        return new FindIdRes(message,userID);
    }

    // 비밀번호 찾기 + , email 발송
    public boolean findPW(FindPwReq findPwReq){
        User user = userRepository.findByUserEmail(findPwReq.getUserEmail());

        if(user.getUserName().equals(findPwReq.getUserName()) // 검증.
                && user.getUserID().equals(findPwReq.getUserID())){

            MailDto dto = sendEmailService.createMailAndChangePassword(user.getUserName()
                    , user.getUserEmail());

            user.updateImplPw(passwordEncoder.encode(dto.getImplPw())); // 임시번호 저장
            userRepository.save(user);

            sendEmailService.mailSend(dto); // 메일 전송

            System.out.println("service.findpw 동작 성공");
            return true;
        }
        System.out.println("service.findpw 동작 실패");

        return false;
    }


}