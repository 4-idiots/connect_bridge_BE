package com.connectbridge.connect_bridge_BE.loginpage.register.controller;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.connectbridge.connect_bridge_BE.teampage.TeamProfileDto;
import com.connectbridge.connect_bridge_BE.teampage.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;
    private final RegisterRepository registerRepository;

    @GetMapping("/team/info/{id}")
    public ResponseEntity<?> team(@PathVariable("id") Long id) throws Exception{
        TeamProfileDto getteampage = teamService.getTeamProfile(id);
        return ResponseEntity.ok(getteampage);
    }

    @GetMapping("/team{page}")
    public ResponseEntity<List<TeamProfileDto>> getPageList(@PathVariable("page") int page, Pageable pageable) {
        System.out.println("페이지 번호" + page);
        List<TeamProfileDto> list = teamService.getList(pageable, page);
        if(list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return ResponseEntity.ok(list);
    }
    /*@GetMapping("/team/{page}")
    public Page<RegisterEntity> getTeam(Pageable pageable, @PathVariable("page") int reqPage){
        pageable = PageRequest.of(reqPage,2, Sort.by(Sort.Direction.DESC,"id"));
        return registerRepository.findAll(pageable);
    }*/
}
