package com.connectbridge.connect_bridge_BE.follow;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class FollowController {
    private final FollowService followService;
    private final FollowRepository followRepository;
    private final JwtProvider jwtProvider;


    @GetMapping("/follow")
    public ResponseEntity<?> followUser(@RequestHeader (value = "Authorization", required = false)String token, @RequestParam(value = "toUserId") Long toUserId) {
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long fromUserId = jwtProvider.getTokenID(tokenResDto.getAccessToken());

            if (followService.likeChk(fromUserId, toUserId)) {
                followService.likeOn(fromUserId, toUserId);
            } else {
                followService.likeOff(fromUserId, toUserId);
            }
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }
}
