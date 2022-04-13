package com.connectbridge.connect_bridge_BE.projectpage.controller;

import com.connectbridge.connect_bridge_BE.outactpage.data.dto.Message;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.*;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;}
/*
    // 메인
    @GetMapping("/project")
    public ResponseEntity<List<ProjectDto>> projectPage() {
        try{
            List<ProjectDto> list = projectService.pageProject();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }
    // 조회
    @GetMapping("/project/{projectID}")
    public ResponseEntity<DetailedDto> projectInquiry(@PathVariable("projectID") Long projectID){
        try{
            return new ResponseEntity<DetailedDto>(projectService.inquiryProject(projectID), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

 */

    // 생성
    @PostMapping("/project")
    public ResponseEntity<Message> projectCreate(@RequestBody CreateDto createReqDto){
        try{
            projectService.createProject(createReqDto);
        return new ResponseEntity(new Message("ok"),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }

    // 삭제
    @DeleteMapping("/project/{projectID}")
    public ResponseEntity<Message> projectDelete(@PathVariable("projectID") Long projectID) {
        try{
            projectService.deleteProject(projectID);
            return new ResponseEntity<Message>(new Message("ok"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<Message>(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }

    // 업데이트 (405 핸들링), userID는 수정해서 보내도 수정 안되게 되어잇음-> 애초에 수정되면 안되니깐.
    @PatchMapping("/project")
    public ResponseEntity<Message> projectUpdate(@RequestBody UpdateReqDto updateDto){
        try{
            projectService.updateProject(updateDto);
            return new ResponseEntity<Message>(new Message("ok"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }
/*
    @PatchMapping("project/apply")
    public ResponseEntity<?> projectApply(@RequestBody ApplyDto applyDto){

        return new ResponseEntity<>(new Message("ok"),HttpStatus.OK);
    }

 */
}

