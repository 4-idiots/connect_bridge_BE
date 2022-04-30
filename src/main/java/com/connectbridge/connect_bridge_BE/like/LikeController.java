package com.connectbridge.connect_bridge_BE.like;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @GetMapping("/outdoor/like/outActID={outActID}&userID={userID}")
    public ResponseEntity<?> outdoorLike(@PathVariable("outActID") Long outActID,
                                         @PathVariable("userID") Long userID) {
        try {
            if (likeService.likeChk(userID,outActID)) {
                System.out.println("likeOn Start");
                likeService.likeOn(userID, outActID);
            } else {
                System.out.println("likeOff Start");
                likeService.likeOff(userID, outActID);
            }
            return new ResponseEntity<>(new Message("ok"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(new Message("no"), HttpStatus.BAD_REQUEST);
        }
    }
}
