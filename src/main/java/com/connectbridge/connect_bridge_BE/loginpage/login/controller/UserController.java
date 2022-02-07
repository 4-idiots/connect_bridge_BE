package com.connectbridge.connect_bridge_BE.loginpage.login.controller;


import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.UserDto;
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

    private final UserService usersService;


    public UserController(UserService usersService){
       this.usersService = usersService;
    }

    @GetMapping("login")
    public String login(){
        return "loginget";
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserDto dto) {
        log.info("userDtoID : {}, userDtoPW : {}",dto.getUserID(),dto.getUserPW());
        if (usersService.login(dto.getUserID(),dto.getUserPW()) == true)
        {
            return new ResponseEntity("ok",HttpStatus.OK);
        }
        return  new ResponseEntity("no",HttpStatus.BAD_REQUEST);

    }
}