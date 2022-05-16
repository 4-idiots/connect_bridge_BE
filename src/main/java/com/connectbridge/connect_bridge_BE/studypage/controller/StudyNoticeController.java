package com.connectbridge.connect_bridge_BE.studypage.controller;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.NoticeDto;
import com.connectbridge.connect_bridge_BE.studypage.Service.StudyNoticeService;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyNoticeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudyNoticeController {

    private final JwtProvider jwtProvider;
    private final StudyNoticeService noticeService;

    public StudyNoticeController(JwtProvider jwtProvider, StudyNoticeService noticeService) {
        this.jwtProvider = jwtProvider;
        this.noticeService = noticeService;
    }

    @PostMapping("/study/notice")
    public ResponseEntity<?> noticePost(@RequestHeader("Authorization") String token, @RequestBody StudyNoticeDto noticeDto){

        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());

            if(noticeService.createNotice(userID, noticeDto)){
                return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
            }
            System.out.println("리더가 아님");
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/study/{studyID}/notice")
    public ResponseEntity<?> noticeGet(@PathVariable("studyID") Long studyID){
        try {
            List<StudyNoticeDto> target = noticeService.mainNotice(studyID);
            return new ResponseEntity<>(noticeService.mainNotice(studyID), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }
}
