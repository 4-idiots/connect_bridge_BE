package com.connectbridge.connect_bridge_BE.loginpage.register.controller;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.TeamProfileDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.service.RegisterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final RegisterImpl registerService;

    @GetMapping("/team/info/{id}")
    public ResponseEntity<?> team(@PathVariable("id") Long id) throws Exception{
        TeamProfileDto getteampage = registerService.getTeamProfile(id);
        return ResponseEntity.ok(getteampage);
    }

    @GetMapping("/team")
    public ResponseEntity<?> getRegisters() throws Exception {
        List<RegisterDto> dtoList = registerService.getRegisters();
        return ResponseEntity.ok(dtoList);
    }
}
