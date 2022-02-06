package com.connectbridge.connect_bridge_BE.loginpage.register.controller;


import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.service.RegisterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class RegisterController {

    private final RegisterImpl registerService;

    @GetMapping(produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<?> getRegisters() throws Exception {
        List<RegisterDto> dtoList = registerService.getRegisters();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/check/userNickname")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@RequestParam String userNickname) throws Exception{
        return ResponseEntity.ok(registerService.checkNicknameDuplicate(userNickname));
    }
    @GetMapping("/check/userID")
    public ResponseEntity<Boolean> checkIdDuplicate(@RequestParam String userID) throws Exception{
        return ResponseEntity.ok(registerService.checkIdDuplicate(userID));
    }
    @GetMapping("/check/userEmail")
    public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String userEmail) throws Exception{
        return ResponseEntity.ok(registerService.checkEmailDuplicate(userEmail));
    }


    @PostMapping("/register")
    public ResponseEntity postRegister(@Valid @RequestBody RegisterDto registerDto) throws Exception {
        registerService.postRegister(registerDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
