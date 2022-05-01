package com.connectbridge.connect_bridge_BE.like;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    private final LikeService likeService;
    private final JwtProvider jwtProvider;

    public LikeController(LikeService likeService, JwtProvider jwtProvider) {
        this.likeService = likeService;
        this.jwtProvider = jwtProvider;
    }


    @GetMapping("/outdoor/like")
    public ResponseEntity<?> outdoorLike(@RequestParam(value = "outActID") Long outActID,
                                         @RequestHeader("Authorization") String token) {

        try {
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());

            if (likeService.likeChk(userID, outActID)) {
                System.out.println("likeOn Start");
                likeService.likeOn(userID, outActID);
            } else {
                System.out.println("likeOff Start");
                likeService.likeOff(userID, outActID);
            }
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }

    // 대외활동 별 좋아요 확인
    @GetMapping("/outdoor/islike/{outActID}")
    public Boolean outdoorIsLike(@RequestHeader(value = "Authorization") String token,
                                               @PathVariable(value = "outActID") Long outActID) {
        try {

            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());

            if (likeService.isLike(userID, outActID)) {
                System.out.println("isLike");
                return true;
            } else {
                System.out.println("noLike");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);

            return false;
        }
        }


}


