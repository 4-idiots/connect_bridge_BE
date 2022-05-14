package com.connectbridge.connect_bridge_BE.studypage.controller;

import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.studypage.Service.StudyManageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudyManageController {
    private final JwtProvider jwtProvider;
    private final StudyManageService manageService;

    public StudyManageController(JwtProvider jwtProvider, StudyManageService manageService) {
        this.jwtProvider = jwtProvider;
        this.manageService = manageService;
    }
/*
    @GetMapping("/study/{studyID}/manage")
    ResponseEntity<?> studyManage(@PathVariable Long studyID){
        try{
            List<>
        }
    }

 */
}
