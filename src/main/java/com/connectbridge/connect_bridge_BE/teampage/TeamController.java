package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.follow.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final FollowRepository followRepository;

    @GetMapping("/team/info/{fromUserId}/{toUserId}")
    public ResponseEntity<?> team(@PathVariable long fromUserId, @PathVariable long toUserId) throws Exception{
        TeamProfileDto getteampage = teamService.getTeamProfile(fromUserId, toUserId);
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
}
