
package com.connectbridge.connect_bridge_BE.projectpage.controller;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.CreateDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.ProjectDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.SubmitDto;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectLikeService;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectLikeService projectLikeService;
    private final S3Service s3Service;
    private final JwtProvider jwtProvider;

    public ProjectController(ProjectService projectService, ProjectLikeService projectLikeService, S3Service s3Service, JwtProvider jwtProvider) {
        this.projectService = projectService;
        this.projectLikeService = projectLikeService;
        this.s3Service = s3Service;
        this.jwtProvider = jwtProvider;
    }

    // 무한 스크롤
    @GetMapping("/project/page/{page}")
    public ResponseEntity<List<ProjectDto>> projectPage(@PathVariable("page") int page, Pageable pageable){
        System.out.println("input page: " + page);
        List<ProjectDto> list = projectService.pagingProject(pageable,page);
        if (list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return ResponseEntity.ok(list);
    }

    // 생성
    @PostMapping("/project")
    public ResponseEntity<Message> projectCreate(
            @RequestParam("projectImg") MultipartFile img,
            CreateDto createDto
    ) throws IOException {
        try {
            String url = s3Service.upload(img, "project");
            createDto.setProjectStrImg(url);
            projectService.createProject(createDto);
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    // 카드 클릭
    @GetMapping("project/{projectID}")
    public ResponseEntity<?> projectDetail(@PathVariable("projectID") Long projectID,
                                           @RequestHeader(value = "Authorization",required = false) String token) {
        try {
            Long userID = Long.valueOf(0);

            if (token != null){
                TokenResDto dto = jwtProvider.tokenManager(token);
                userID = jwtProvider.getTokenID(dto.getAccessToken());
                System.out.println("Cont1 userID : "+ userID);

            }
            System.out.println("Cont2 userID : "+userID);

            return new ResponseEntity<>(projectService.detailProject(projectID, userID), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("그곳이 여기인가?: "+e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    // 업데이트
    @PatchMapping("/project")
    public ResponseEntity<?> projectUpdate(@RequestParam("projectID") Long projectID,
                                           @RequestParam("projectImg") MultipartFile projectImg,
                                           CreateDto createDto) throws IOException {

        boolean valid = projectService.updateProject(projectID, projectImg, createDto);

        if (valid) {
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
    }

    // 삭제
    @DeleteMapping("/project/{projectID}")
    public ResponseEntity<?> projectDelete(@PathVariable("projectID") Long projectID){
        if(projectService.deleteProject(projectID)){
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        }
            return new ResponseEntity<>(new Message("no"),HttpStatus.BAD_REQUEST);
    }

    //프로젝트 지원
    @PatchMapping("/project/apply")
    public ResponseEntity<?> submitProject(@RequestBody SubmitDto submitDto) {
            if(projectService.submitProject(submitDto)){
                return new ResponseEntity<>(new Message("ok"),HttpStatus.OK);
            }
            return new ResponseEntity<>(new Message("no"),HttpStatus.BAD_REQUEST);
    }

    //좋아요 통신
    @GetMapping("/project/like")
    public ResponseEntity<?> projectLike(@RequestParam(value = "projectID") Long projectID,
                                         @RequestHeader("Authorization") String token){

        try{
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());

            if(projectLikeService.likeChk(userID, projectID)){
                System.out.println("P likeOn Start");
                projectLikeService.likeOn(userID,projectID);
            }else{
                System.out.println("P likeOff Start");
                projectLikeService.likeOff(userID,projectID);
            }
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    //좋아요 구분 통신
    @GetMapping("/project/islike/{projectID}")
    public Boolean projectLike(@RequestHeader(value = "Authorization") String token,
                               @PathVariable(value = "projectID") Long projectID){
        try{
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());

            if (projectLikeService.isLike(userID,projectID)) {
                System.out.println("P isLike");
                return true;
            } else {
                System.out.println("P noLike");
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    // 상세 페이지-공지
    @PostMapping("project/notice")
    public ResponseEntity<?> projectNotice(@RequestParam(value = "content") String content,
                                           @RequestParam(value = "projectID") Long projectID,
                                           @RequestHeader("Authorization") String token) {

        TokenResDto tokenResDto = jwtProvider.tokenManager(token);
        Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());




        return new ResponseEntity<>(new Message("ok"),HttpStatus.OK);
    }

}

