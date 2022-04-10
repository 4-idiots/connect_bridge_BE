package com.connectbridge.connect_bridge_BE.community.like;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@Slf4j
public class CommunityLikeController {
    private final CommunityLikeRepository communityLikeRepository;
    private final CommunityLikeService communityLikeService;

    @GetMapping("/community/like/{fromUserId}/{toPostId}")
    public ResponseEntity<Boolean> followUser(@PathVariable long fromUserId, @PathVariable long toPostId) {
        if(communityLikeRepository.findByFromUserIdAndToPostId(fromUserId, toPostId) != null){
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        communityLikeService.save(fromUserId, toPostId);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    @DeleteMapping("/community/like/{fromUserId}/{toPostId}")
    public ResponseEntity<?> unFollowUser(@PathVariable int fromUserId, @PathVariable long toPostId) {
        Long id = communityLikeService.unLike(fromUserId, toPostId);
        communityLikeRepository.deleteById(id);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
