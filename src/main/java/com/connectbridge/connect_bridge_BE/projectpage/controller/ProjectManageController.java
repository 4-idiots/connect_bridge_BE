package com.connectbridge.connect_bridge_BE.projectpage.controller;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectManageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProjectManageController {

    private final JwtProvider jwtProvider;
    private final ProjectManageService projectManageService;


    public ProjectManageController(JwtProvider jwtProvider, ProjectManageService projectManageService) {
        this.jwtProvider = jwtProvider;
        this.projectManageService = projectManageService;
    }

    // manageMain
    @GetMapping("/project/{projectID}/manage")
    ResponseEntity<?> managePage(@PathVariable Long projectID) {
        try {
            List<HashMap<String, Object>> subInfo = projectManageService.findSubInfo(projectID);
            return new ResponseEntity<>(subInfo, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    // onClikc Apply
    @GetMapping("/project/{projectID}/apply/{submitID}")
    ResponseEntity<?> apply(@PathVariable Long projectID,
                            @PathVariable Long submitID,
                            @RequestHeader("Authorization") String token) {
        try {
            // 검증, get userID
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            boolean input = true;
            // service run
            boolean result = projectManageService.manageSub(projectID, submitID, userID, input);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // onClick Reject
    @GetMapping("/project/{projectID}/reject/{submitID}")
    ResponseEntity<?> reject(@PathVariable Long projectID,
                             @PathVariable Long submitID,
                             @RequestHeader("Authorization") String token) {
        try {
            // 검증, get userID
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            boolean input = false;
            // service run
            boolean result = projectManageService.manageSub(projectID, submitID, userID, input);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);

            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/project/{projectID}/manage/end")
    ResponseEntity<?> projectEnd(@RequestHeader("Authorization") String token,
                                 @PathVariable("projectID") Long projectID) {
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            projectManageService.endProject(userID, projectID);
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/project/{projectID}/manage/fire/{memberID}")
    ResponseEntity<?> memberFire(@RequestHeader("Authorization") String token,
                                 @PathVariable("projectID") Long projectID, @PathVariable("memberID") Long memberID) {
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            if(projectManageService.fireMember(userID, projectID, memberID)){
                return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
            }
                return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }
}

