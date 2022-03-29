package com.connectbridge.connect_bridge_BE.projectpage.controller;

import com.connectbridge.connect_bridge_BE.outactpage.data.dto.Message;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.CreateReqDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.ProjectDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.UpdateReqDto;
import com.connectbridge.connect_bridge_BE.projectpage.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    /*
    pageapble 필요없고 그냥 다주면 됨.-> 배열형태로.

    @GetMapping("/project/{page}")// 이거 아마 통신 바꿔야 할 듯?
    public ResponseEntity<List<ProjectDto>> projectpageList() {
        try{
            return new ResponseEntity<List<ProjectDto>>(projectService.providePage(), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity(new Message("no"),HttpStatus.BAD_REQUEST);
        }

    }
*/
    // 생성 -> List로 받는 것 아직 안함.
    @PostMapping("/project")
    public ResponseEntity<Message> projectCreate(@RequestBody CreateReqDto createDto){
        try{
            projectService.createProject(createDto);
            return new ResponseEntity<Message>(new Message("ok"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<Message>(new Message("no"),HttpStatus.BAD_REQUEST);
        }
    }

    // 삭제 (405 핸들링)
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
    @PatchMapping("/project/apply")
    public ResponseEntity<Message> projectApply(@RequestBody Long userID,
                                                @RequestBody Long projectID){
    }

     */
}
