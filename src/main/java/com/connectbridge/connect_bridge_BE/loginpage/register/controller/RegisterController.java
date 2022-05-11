package com.connectbridge.connect_bridge_BE.loginpage.register.controller;


import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.service.RegisterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RegisterController {

    private final RegisterImpl registerService;


    @GetMapping(produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<String> getRegister() throws Exception{
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/users/check/userNickname")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@RequestParam String userNickname) throws Exception{
        Boolean checkNick = registerService.checkNicknameDuplicate(userNickname);

        return new ResponseEntity(checkNick, HttpStatus.OK);
    }
    @GetMapping("/users/check/userID")
    public ResponseEntity<Boolean> checkIdDuplicate(@RequestParam String userID) throws Exception{
        Boolean checkId = registerService.checkIdDuplicate(userID);

        return new ResponseEntity(checkId, HttpStatus.OK);
    }
    @GetMapping("/users/check/userEmail")
    public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String userEmail) throws Exception{

        Boolean checkEmail = registerService.checkEmailDuplicate(userEmail);

        return new ResponseEntity(checkEmail, HttpStatus.OK);
    }


    @PostMapping("/users/register")
    public ResponseEntity postRegister(@RequestBody RegisterDto registerDto) throws Exception {
        registerService.postRegister(registerDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
        }

}
