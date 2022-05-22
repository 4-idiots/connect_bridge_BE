package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.follow.FollowRepository;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TeamController {

    private final TeamService teamService;
    private final JwtProvider jwtProvider;

    @GetMapping("/team/info/{toUserId}")
    public ResponseEntity<?> team(@RequestHeader(value = "Authorization", required = false)String token, @PathVariable long toUserId) throws Exception{
        Long fromUserId = Long.valueOf(0);
        if (!jwtProvider.extractToken(token).equals("null")) {
            TokenResDto dto = jwtProvider.tokenManager(token);
            fromUserId = jwtProvider.getTokenID(dto.getAccessToken());
        }
        TeamProfileDto getteampage = teamService.getTeamProfile(fromUserId, toUserId);
        return ResponseEntity.ok(getteampage);
    }


    @GetMapping("/team{page}")
    public ResponseEntity<List<TeamMainDto>> getPageList(@PathVariable("page") int page, Pageable pageable) {
        List<TeamMainDto> list = teamService.getList(pageable, page);
        if(list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/team/{area}/{interest}")
    public ResponseEntity<List<TeamMainDto>> getAreaPage(@PathVariable("area") String area, @PathVariable("interest") String interest) {
        List<TeamMainDto> list = teamService.getArea(area, interest);
        if(list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return ResponseEntity.ok(list);
    }
}
