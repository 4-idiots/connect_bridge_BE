package com.connectbridge.connect_bridge_BE.loginpage.register.controller;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.*;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.EmailCodeRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.loading.MLetContent;
import java.util.Map;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/users/check/Email") // 이메일 인증 코드 보내기
    public ResponseEntity<String> emailAuth(@RequestBody Map<String, String> emailCodeDto) throws Exception {
        log.info("emailCodeDto : {}", emailCodeDto);
        emailService.sendSimpleMessage(emailCodeDto.get("userEmail"));
        Message message = Message.builder()
                .message("ok")
                .build();

        return new ResponseEntity(message, HttpStatus.OK);
    }

    @PostMapping("/verifycode") // 이메일 인증 코드 검증
    public ResponseEntity<?> verifyCode(@RequestBody EmailCodeDto emailCodeDto) throws Exception{
        String getcode = emailCodeDto.getCode();
        log.info("getcode : {}", getcode);
        String getemail = emailCodeDto.getUserEmail();
        log.info("getemail : {}", getemail);
        if(emailService.checkCode(getcode, getemail)){
            Message message = Message.builder()
                    .message("ok")
                    .build();
            return new ResponseEntity(message, HttpStatus.OK);
        }
        else{
            Message message = Message.builder()
                    .message("no")
                    .build();
            return new ResponseEntity(message, HttpStatus.OK);
        }
    }
}
