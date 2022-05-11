package com.connectbridge.connect_bridge_BE.studypage.controller;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.Message;
import com.connectbridge.connect_bridge_BE.studypage.Service.StudyService;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyCreateDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudyController {

    private final StudyService studyService;
    private final JwtProvider jwtProvider;

    public StudyController(StudyService studyService, JwtProvider jwtProvider) {
        this.studyService = studyService;
        this.jwtProvider = jwtProvider;
    }

    // mainPage infi
    @GetMapping("/study/page/{page}")
    public ResponseEntity<List<StudyDto>> studyPage(@PathVariable("page") int page, Pageable pageable) {
        List<StudyDto> list = studyService.studyGetPage(pageable, page);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // mainPage Top4
    @GetMapping("/study/page/new")
    public ResponseEntity<List<StudyDto>> studyTop() {
        try {
            List<StudyDto> list = studyService.studyGetTop();
            if (list.isEmpty()) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    // study post
    @PostMapping("/study")
    public ResponseEntity<?> studyPost(@RequestBody StudyCreateDto studyCreateDto) {
        try {
            studyService.createStudy(studyCreateDto);
            return new ResponseEntity<>(new com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message("no"), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/study/{studyID}")
    public ResponseEntity<?> studyDetail(@PathVariable("studyID") Long studyID,
        @RequestHeader(value = "Authorization", required = false) String token){

        try{
            Long userID = Long.valueOf(0);
            if (!jwtProvider.extractToken(token).equals("null")) {
                TokenResDto dto = jwtProvider.tokenManager(token);
                userID = jwtProvider.getTokenID(dto.getAccessToken());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Message("no"),HttpStatus.BAD_REQUEST);
        }

    }

}
