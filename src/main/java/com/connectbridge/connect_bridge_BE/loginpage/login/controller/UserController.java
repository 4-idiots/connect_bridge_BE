package com.connectbridge.connect_bridge_BE.loginpage.login.controller;


import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.FindIdReq;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.FindIdRes;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.LoginReqDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.loginpage.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    private final JwtProvider jwtProvider;

    public UserController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("login")
    public String login(){
        return "loginget";
    }

    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginReqDto reqDto){
        try{
            TokenDto token = userService.login(reqDto);
            System.out.println("service.login 동작성공.");
            return new ResponseEntity<TokenDto>(token,HttpStatus.OK);
        }catch (Exception e){
            System.out.println("service.login 동작실패.");
            return new ResponseEntity("no",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("user/findID")
    public ResponseEntity<FindIdRes> findId(@RequestBody FindIdReq findIdReq){
            FindIdRes findIdRes = userService.findID(findIdReq);
            log.info("reqName : {}, reqPh : {} , reqEm : {}",findIdReq.getUserName(),findIdReq.getUserPhone(),findIdReq.getUserEmail());
            if(findIdRes.getMessage() == "ok" ) {
                return new ResponseEntity<FindIdRes>(findIdRes, HttpStatus.OK);
            }
            return new ResponseEntity<FindIdRes>(findIdRes,HttpStatus.BAD_REQUEST);
            //NoSuchException 처리하기.
    }
}