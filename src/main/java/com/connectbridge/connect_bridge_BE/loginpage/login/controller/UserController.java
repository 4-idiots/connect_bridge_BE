package com.connectbridge.connect_bridge_BE.loginpage.login.controller;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.*;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.loginpage.login.service.SendEmailService;
import com.connectbridge.connect_bridge_BE.loginpage.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, JwtProvider jwtProvider, SendEmailService sendEmailService) {
        this.userService = userService;
    }

    @PostMapping("user/login")
    public ResponseEntity<TokenResDto> login(@RequestBody LoginReqDto reqDto) {
        try {
            TokenResDto loginResDto = userService.login(reqDto);
            System.out.println("controller-login 동작성공.");

            return new ResponseEntity<TokenResDto>(loginResDto, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("controller-login 동작실패: " + e);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("user/findID")
    public ResponseEntity<FindIdRes> findId(@RequestBody FindIdReq findIdReq) {
        FindIdRes findIdRes = userService.findID(findIdReq);
        if (findIdRes.getMessage().equals("ok")) {

            return new ResponseEntity<FindIdRes>(findIdRes, HttpStatus.OK);
        }

        return new ResponseEntity<FindIdRes>(findIdRes, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("user/findPW")
    public ResponseEntity findPwByEmail(@RequestBody FindPwReq findPwReq) {
            if (userService.findPW(findPwReq)) {

                Message message = Message.builder()
                        .message("ok")
                        .build();

                return new ResponseEntity(message, HttpStatus.OK);

            }else{

            Message message = Message.builder()
                    .message("no")
                    .build();

            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

}
