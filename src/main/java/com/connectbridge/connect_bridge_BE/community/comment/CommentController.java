package com.connectbridge.connect_bridge_BE.community.comment;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final JwtProvider jwtProvider;

    @PostMapping("/community/comment")
    public ResponseEntity commentPost(@RequestBody CommentDto commentDto, @RequestHeader (value = "Authorization", required = false)String token)throws Exception{
        TokenResDto tokenResDto = jwtProvider.tokenManager(token);
        Long fromUserId = jwtProvider.getTokenID(tokenResDto.getAccessToken());
        commentService.addComment(commentDto, fromUserId);
        commentService.commentcounting(commentDto.getPostID());
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/community/comment")
    private ResponseEntity commentupdate (@RequestBody CommentDto commentDto)throws Exception{
        commentService.updateComment(commentDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/community/comment/{id}/{postID}")
    public ResponseEntity commentDelete(@PathVariable long id, @PathVariable long postID) throws Exception{
        commentService.deleteComment(id);
        commentService.commentcounting(postID);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity(message, HttpStatus.OK);
    }

}
