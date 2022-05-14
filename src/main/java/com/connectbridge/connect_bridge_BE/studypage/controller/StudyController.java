package com.connectbridge.connect_bridge_BE.studypage.controller;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.Message;
import com.connectbridge.connect_bridge_BE.studypage.Service.StudyLikeService;
import com.connectbridge.connect_bridge_BE.studypage.Service.StudyService;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyCreateDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudySubmitDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudyController {

    private final StudyService studyService;
    private final StudyLikeService studyLikeService;
    private final JwtProvider jwtProvider;
    private final S3Service s3Service;

    public StudyController(StudyService studyService, StudyLikeService studyLikeService, JwtProvider jwtProvider, S3Service s3Service) {
        this.studyService = studyService;
        this.studyLikeService = studyLikeService;
        this.jwtProvider = jwtProvider;
        this.s3Service = s3Service;
    }

    // mainPage infi
    @GetMapping("/study/page/{page}")
    public ResponseEntity<List<StudyDto>> studyPage(@PathVariable("page") int page, Pageable pageable) {
        try{
        List<StudyDto> list = studyService.studyGetPage(pageable, page);
        return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity(new Message("no"),HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<?> studyPost(
            @RequestParam(value = "studyImg", required = false) MultipartFile img,
            StudyCreateDto studyCreateDto,
            @RequestHeader("Authorization") String token) {
        try {
            TokenResDto dto = jwtProvider.tokenManager(token);
            studyCreateDto.setUserID(jwtProvider.getTokenID(dto.getAccessToken()));

            String url = s3Service.upload(img, "study");
            studyCreateDto.setStudyStrImg(url);
            studyService.createStudy(studyCreateDto);

            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);

            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    // study click
    @GetMapping("/study/{studyID}")
    public ResponseEntity<?> studyDetail(@PathVariable("studyID") Long studyID,
                                         @RequestHeader(value = "Authorization", required = false) String token) {
        try {
            Long userID = Long.valueOf(0);
            if (!jwtProvider.extractToken(token).equals("null")) {
                TokenResDto dto = jwtProvider.tokenManager(token);
                userID = jwtProvider.getTokenID(dto.getAccessToken());
            }
            return new ResponseEntity<>(studyService.clickStudy(studyID, userID), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST
            );
        }
    }

    //study update
    @PatchMapping("/study")
    public ResponseEntity<?> studyUpdate(@RequestParam("studyID") Long studyID,
                                         @RequestParam("studyImg") MultipartFile studyImg, StudyCreateDto createDto) throws IOException {

        if (studyService.updateStudy(studyID, studyImg, createDto)) {
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
    }

    // study delete
    @DeleteMapping("/study/{studyID}")
    public ResponseEntity<?> studyDelete(@PathVariable("studyID") Long studyID,
                                         @RequestHeader("Authorization") String token) {
        TokenResDto dto = jwtProvider.tokenManager(token);
        Long userID = jwtProvider.getTokenID(dto.getAccessToken());
        if (studyService.deleteStudy(studyID, userID)) {
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
    }

    // like onoff
    @GetMapping("/study/like")
    public ResponseEntity<?> studyLike(@RequestHeader("Authorization") String token, @RequestParam(value = "studyID") Long studyID) {
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());

            if (studyLikeService.likeChk(userID, studyID)) {
                studyLikeService.likeOn(userID, studyID);
            } else {
                studyLikeService.likeOff(userID, studyID);
            }
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);

        }
    }

    // like chk
    @GetMapping("/study/islike/{studyID}")
    public ResponseEntity<Boolean> studyIsLike(@RequestHeader(value = "Authorization") String token,
                                               @PathVariable(value = "studyID") Long studyID) {
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());

            return new ResponseEntity<>(studyLikeService.isLike(userID, studyID),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }

    // study apply
    @GetMapping("/study/apply")
    public ResponseEntity<?> studySubmit(@RequestBody StudySubmitDto submitDto,
        @RequestHeader("Authorization") String token){
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            submitDto.setUserID(userID);

            if(studyService.submitStudy(submitDto)){
                return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Message("no"),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }
}
