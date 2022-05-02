package com.connectbridge.connect_bridge_BE.projectpage.controller;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.CreateDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.SubmitDto;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ProjectController {

    private final ProjectService projectService;
    private final S3Service s3Service;

    public ProjectController(ProjectService projectService, S3Service s3Service) {
        this.projectService = projectService;
        this.s3Service = s3Service;
    }


    @GetMapping("/project")
    public ResponseEntity<?> projectPage(){
        try{
            return new ResponseEntity<>(projectService.pagingProject(),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"),HttpStatus.BAD_REQUEST);
        }

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
    public ResponseEntity<?> projectDetail(@PathVariable("projectID") Long projectID) {
        try {
            return new ResponseEntity<>(projectService.detailProject(projectID), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
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

    @PatchMapping("/project/apply")
    public ResponseEntity<?> submitProject(@RequestBody SubmitDto submitDto) {
            if(projectService.submitProject(submitDto)){
                return new ResponseEntity<>(new Message("ok"),HttpStatus.OK);
            }
            return new ResponseEntity<>(new Message("no"),HttpStatus.BAD_REQUEST);
    }



}
