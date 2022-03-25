package com.connectbridge.connect_bridge_BE.projectpage.controller;

import com.connectbridge.connect_bridge_BE.outactpage.data.dto.Message;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.CreateReqDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.PatchInfoDto;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // 생성 -> List로 받는 것 아직 안함.
    @PostMapping("/project")
    public ResponseEntity<Message> projectCreate(@RequestBody CreateReqDto createReqDto){
        try{
        projectService.createProject(createReqDto);

        return new ResponseEntity<Message>(new Message("ok"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<Message>(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }

    // 삭제 값 안넣으면 405. -> 핸들링
    @DeleteMapping("/project/{projectID}")
    public ResponseEntity<Message> projectDelete(@PathVariable("projectID")Long projectID) {
        try{
            projectService.deleteProject(projectID);
            return new ResponseEntity<Message>(new Message("ok"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<Message>(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }

    // 수정에 필요한 정보 제공
    @GetMapping("/project/update/{projectID}")
    public ResponseEntity<PatchInfoDto> projectPathInfo(@PathVariable("projectID")Long projectID){
        try{
        PatchInfoDto info = projectService.provideInfo(projectID);
        return new ResponseEntity<PatchInfoDto>(info, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }

}
