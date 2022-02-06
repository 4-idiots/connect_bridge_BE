package com.connectbridge.connect_bridge_BE.loginpage.login.controller;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.LoginDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.LoginEntity;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.LoginRepository;
import com.connectbridge.connect_bridge_BE.loginpage.login.service.LoginServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private LoginServiceImpl loginService;
    private LoginRepository loginRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody LoginDto dto) throws Exception {
        LoginEntity tEntity = new LoginEntity();
        tEntity = loginRepository.findByUserIDAndUserPW(dto.getUserID(),dto.getUserPW());
        System.out.println(dto.getUserID() + dto.getUserPW());
        return "login";
    }
}
