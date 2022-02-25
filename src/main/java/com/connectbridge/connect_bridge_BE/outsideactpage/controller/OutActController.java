package com.connectbridge.connect_bridge_BE.outsideactpage.controller;

import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.Message;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.ModifyResDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.PostCreateDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.UpdateReqDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.service.OutActService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OutActController {

    private final OutActService outActService;

    public OutActController(OutActService outActService) {
        this.outActService = outActService;
    }


    @GetMapping("/outdoor")
    public ResponseEntity getActPage(){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 생성
    @PostMapping("/outdoor/post")
    public ResponseEntity<Message> postActPage(@RequestBody PostCreateDto requestDto){

            if(outActService.createPost(requestDto) == true){

            Message message = Message.builder()
                    .message("ok")
                    .build();

            return new ResponseEntity<>(message,HttpStatus.OK);
            }else {
                Message message = Message.builder()
                        .message("no")
                        .build();

                return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
            }

    }

    // 수정 조회
    @GetMapping("/outdoor/post/{outActID}")
    public ResponseEntity<ModifyResDto> selectModify(@PathVariable("outActID") Long id){
        ModifyResDto responseDto = outActService.modifyInfo(id);

        return new ResponseEntity<ModifyResDto>(responseDto,HttpStatus.OK);
    }

    // 수정
    @PatchMapping("outdoor/post")
    public ResponseEntity<UpdateReqDto>  postUpdate(@RequestBody UpdateReqDto requestDto){
        try{
            outActService.updatePost(requestDto);
            Message message = Message.builder()
                    .message("ok")
                    .build();

            return new ResponseEntity(message,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);

            Message message = Message.builder()
                    .message("no")
                    .build();

            return new ResponseEntity(message,HttpStatus.BAD_REQUEST);

        }
    }

    // 삭제
    @DeleteMapping("/outdoor/post/{outActID}")
    public ResponseEntity<Message> deletePost(@PathVariable("outActID") Long id){
            if(outActService.deletePost(id)){

                Message message = Message.builder()
                        .message("ok")
                        .build();

                return new ResponseEntity<>(message,HttpStatus.OK);
            }else{

                Message message = Message.builder()
                        .message("no")
                        .build();

                return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
            }
    }
}


