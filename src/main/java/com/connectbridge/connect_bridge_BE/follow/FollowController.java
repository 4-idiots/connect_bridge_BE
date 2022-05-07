package com.connectbridge.connect_bridge_BE.follow;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
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

    @GetMapping("/follow/{fromUserId}/{toUserId}")
    public ResponseEntity<Boolean> followUser(@PathVariable long fromUserId, @PathVariable long toUserId) {
        if (fromUserId == toUserId){
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        if(followRepository.findFollowByFromUserIdAndToUserId(fromUserId, toUserId) != null){
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        followService.save(fromUserId, toUserId);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    @DeleteMapping("/follow/{fromUserId}/{toUserId}")
    public ResponseEntity<?> unFollowUser(@PathVariable int fromUserId, @PathVariable long toUserId) {
        Long id = followService.unFollow(fromUserId, toUserId);
        followRepository.deleteById(id);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
