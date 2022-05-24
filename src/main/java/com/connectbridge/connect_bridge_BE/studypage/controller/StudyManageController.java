package com.connectbridge.connect_bridge_BE.studypage.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.Message;
import com.connectbridge.connect_bridge_BE.studypage.Service.StudyManageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudyManageController {
    private final JwtProvider jwtProvider;
    private final StudyManageService manageService;

    public StudyManageController(JwtProvider jwtProvider, StudyManageService manageService) {
        this.jwtProvider = jwtProvider;
        this.manageService = manageService;
    }

    //managePage Main
    @GetMapping("/study/{studyID}/manage")
    ResponseEntity<?> studyManage(@PathVariable Long studyID) {
        try {
            List<HashMap<String, Object>> subInfo = manageService.findSubInfo(studyID);
            return new ResponseEntity<>(subInfo, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    // On Click Apply
    @GetMapping("/study/{studyID}/apply/{submitID}")
    ResponseEntity<?> apply(@PathVariable Long studyID,
                            @PathVariable Long submitID,
                            @RequestHeader("Authorization") String token) {
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            boolean input = true;
            boolean result = manageService.manageSub(studyID,submitID,userID, input);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    // On Click Reject
    @GetMapping("/study/{studyID}/reject/{submitID}")
    ResponseEntity<?> reject(@PathVariable Long studyID,
                            @PathVariable Long submitID,
                            @RequestHeader("Authorization") String token) {
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            boolean input = false;
            boolean result = manageService.manageSub(studyID,submitID,userID, input);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    // On Click End
    @PatchMapping("/study/{studyID}/manage/end")
    ResponseEntity<?> studyEnd(@RequestHeader("Authorization") String token,
        @PathVariable("studyID") Long studyID){
    try{
        TokenResDto tokenResDto = jwtProvider.tokenManager(token);
        Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
        manageService.endStudy(userID, studyID);
        return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
    }catch (Exception e){
        System.out.println(e);
        return new ResponseEntity<>(new Message("no"),HttpStatus.BAD_REQUEST);
    }
    }

    // On Click Fire
    @GetMapping("/study/{studyID}/manage/fire/{memberID}")
    ResponseEntity<?> memberFire(@RequestHeader("Authorization")String token,
        @PathVariable("studyID") Long studyID,@PathVariable("memberID") Long memberID){
        try{

            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            if(manageService.fireStudyMember(userID, studyID,memberID)){
                return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);

        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message("no"), HttpStatus.BAD_REQUEST);
        }
    }
}
