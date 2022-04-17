package com.connectbridge.connect_bridge_BE.community.comment;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/community/comment/{fromUserId}")
    public ResponseEntity commentPost(@RequestBody CommentDto commentDto, @PathVariable long fromUserId)throws Exception{
        commentService.addComment(commentDto, fromUserId);
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

    @DeleteMapping("/community/comment/{id}")
    public ResponseEntity commentDelete(@PathVariable long id) throws Exception{
        commentService.deleteComment(id);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity(message, HttpStatus.OK);
    }

}
