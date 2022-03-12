package com.connectbridge.connect_bridge_BE.outsideactpage.controller;

import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.*;
import com.connectbridge.connect_bridge_BE.outsideactpage.service.OutActService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class OutActController {

    private final OutActService outActService;

    public OutActController(OutActService outActService) {
        this.outActService = outActService;
    }

    @GetMapping("/outdoor/{page}")
    public ResponseEntity<List<OutActDto>> getPageList(
            @PathVariable("page") int page,
            Pageable pageable) { // IllegalArgumentException 잡아야함.

            List<OutActDto> list = outActService.getList(pageable, page);

            if(list.isEmpty()){
                //return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            return ResponseEntity.ok(list);
    }

    // 생성
    @PostMapping(value = "/outdoor/post")
    public ResponseEntity<Message> postActPage(
            @RequestParam("outActImg") MultipartFile img,
            @RequestParam("outActName") String title,
            @RequestParam("outActLink") String link
            ) throws IOException {

        try {
            PostCreateDto request = PostCreateDto.builder()
                    .outActImg(img)
                    .outActLink(link)
                    .outActName(title)
                    .build();

            outActService.createPost(request);

            Message message = Message.builder()
                    .message("ok")
                    .build();

            return new ResponseEntity<>(message,HttpStatus.OK);

        }catch (Exception e){

            Message message = Message.builder()
                    .message("no")
                    .build();

            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
        }

    // 수정 조회
    @GetMapping("/outdoor/post/{outActID}")
    public ResponseEntity<ModifyResDto> selectModify(@PathVariable("outActID") Long id){
        try{
        ModifyResDto responseDto = outActService.modifyInfo(id);

        return new ResponseEntity<ModifyResDto>(responseDto,HttpStatus.OK);

        }catch (Exception e)
        {
            System.out.println(e);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    // 수정
    @PatchMapping("outdoor/post")
    public ResponseEntity postUpdate(@RequestParam("outActID") Long id,
                                     @RequestParam("outActName") String title,
                                     @RequestParam("outActLink") String link,
                                     @RequestParam("outActImg") MultipartFile img
                                     ){
        try{
            UpdateReqDto requestDto = UpdateReqDto.builder()
                    .outActID(id)
                    .outActName(title)
                    .outActLink(link)
                    .outActImg(img)
                    .build();

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