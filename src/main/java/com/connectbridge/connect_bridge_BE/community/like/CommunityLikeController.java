package com.connectbridge.connect_bridge_BE.community.like;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class CommunityLikeController {
    private final CommunityLikeService communityLikeService;
    private final JwtProvider jwtProvider;

    @GetMapping("/community/like")
    public ResponseEntity<?> followCommunity(@RequestHeader (value = "Authorization")String token, @RequestParam(value = "toPostId") Long toPostId) {
        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long fromUserId = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            if (communityLikeService.likeChk(fromUserId, toPostId)) {
                communityLikeService.likeOn(fromUserId, toPostId);
            } else {
                communityLikeService.likeOff(fromUserId, toPostId);
            }
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }
}
