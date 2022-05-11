package com.connectbridge.connect_bridge_BE.mypage;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.community.CommunityPreviewDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.UpdateRegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.UpdateRegisterDto2;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.UpdateReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/my/info/{fromUserId}") //내 정보 불러오기
    public ResponseEntity<?> myinfoPage(@PathVariable long fromUserId) throws Exception{
        RegisterDto registerDto = myPageService.getMyPage(fromUserId);

        return new ResponseEntity<>(registerDto, HttpStatus.OK);
    }

    @PatchMapping("/my/info") // 내 정보 수정하기
    public ResponseEntity<?> myinfoFix(UpdateRegisterDto updateRegisterDto) throws Exception{
        myPageService.updateMyInfo(updateRegisterDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping("/my/info") // 내 정보 수정하기 이미지빼고
    public ResponseEntity<?> myinfoFix2(@RequestBody UpdateRegisterDto2 updateRegisterDto2) throws Exception{
        System.out.println(updateRegisterDto2.getId()+updateRegisterDto2.getUserNickname()+
                updateRegisterDto2.getUserAbility() + updateRegisterDto2.getUserArea()+
                updateRegisterDto2.getUserTime()+ updateRegisterDto2.getUserInterestMain()+
                updateRegisterDto2.getUserInterestSub()+ updateRegisterDto2.getUserIntroduce()+
                updateRegisterDto2.getUserPortfolio());
        myPageService.updateMyInfo2(updateRegisterDto2);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/mycommunity/{fromUserId}")
    public ResponseEntity<?> myCommunity(@PathVariable long fromUserId) throws Exception{
        return new ResponseEntity<>(myPageService.getCommunityPage(fromUserId), HttpStatus.OK);
    }
}
