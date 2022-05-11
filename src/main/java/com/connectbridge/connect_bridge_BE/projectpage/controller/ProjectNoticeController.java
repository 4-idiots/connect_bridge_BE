package com.connectbridge.connect_bridge_BE.projectpage.controller;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.NoticeDto;
import com.connectbridge.connect_bridge_BE.projectpage.repository.NoticeMapping;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectNoticeController {

    private final JwtProvider jwtProvider;
    private final ProjectNoticeService projectNoticeService;

    public ProjectNoticeController(JwtProvider jwtProvider, ProjectNoticeService projectNoticeService) {
        this.jwtProvider = jwtProvider;
        this.projectNoticeService = projectNoticeService;
    }


    @PostMapping("/project/notice")
    public ResponseEntity<?> noticePost(@RequestHeader("Authorization") String token, @RequestBody NoticeDto noticeDto) {
        System.out.println(noticeDto.getContent() + " " + noticeDto.getProjectID());
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            boolean serviceChk = projectNoticeService.createNotice(userID, noticeDto);

            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/project/{projectID}/notice")
    public ResponseEntity<?> noticeGet(@PathVariable("projectID") Long projectID) {
        try {
            return new ResponseEntity<>(projectNoticeService.mainNotice(projectID), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
