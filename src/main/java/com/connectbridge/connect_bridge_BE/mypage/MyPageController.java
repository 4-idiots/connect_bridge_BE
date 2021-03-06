package com.connectbridge.connect_bridge_BE.mypage;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.community.CommunityPreviewDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
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
    private final JwtProvider jwtProvider;

    @GetMapping("/my/info") //내 정보 불러오기
    public ResponseEntity<?> myinfoPage(@RequestHeader (value = "Authorization", required = false)String token) throws Exception{
        TokenResDto tokenResDto = jwtProvider.tokenManager(token);
        Long fromUserId = jwtProvider.getTokenID(tokenResDto.getAccessToken());
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

    @GetMapping("/mycommunity")
    public ResponseEntity<?> myCommunity(@RequestHeader (value = "Authorization", required = false)String token) throws Exception{
        TokenResDto tokenResDto = jwtProvider.tokenManager(token);
        Long fromUserId = jwtProvider.getTokenID(tokenResDto.getAccessToken());
        return new ResponseEntity<>(myPageService.getCommunityPage(fromUserId), HttpStatus.OK);
    }

    @GetMapping("/myproject")
    public ResponseEntity<?> myProject(@RequestHeader(value = "Authorization", required = false) String token){
        try{

        TokenResDto tokenResDto = jwtProvider.tokenManager(token);
        Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
        return new ResponseEntity<>(myPageService.getProjectInfo(userID),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mystudy")
    public ResponseEntity<?> myStudy(@RequestHeader(value = "Authorization", required = false) String token){
        try{
            TokenResDto tokenResDto = jwtProvider.tokenManager(token);
            Long userID = jwtProvider.getTokenID(tokenResDto.getAccessToken());
            return new ResponseEntity<>(myPageService.getStudyInfo(userID),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/myfollow")
    public ResponseEntity<?> myFollow(@RequestHeader (value = "Authorization", required = false)String token) throws Exception{
        TokenResDto tokenResDto = jwtProvider.tokenManager(token);
        Long fromUserId = jwtProvider.getTokenID(tokenResDto.getAccessToken());
        return new ResponseEntity<>(myPageService.myPageSub(fromUserId), HttpStatus.OK);
    }
    @GetMapping("/team/islike/{teamID}")
    public ResponseEntity<?> teamLike(@RequestHeader (value = "Authorization", required = false)String token, @PathVariable long teamID) throws Exception{
        Long fromUserId = Long.valueOf(0);
        if (!jwtProvider.extractToken(token).equals("null")) {
            TokenResDto dto = jwtProvider.tokenManager(token);
            fromUserId = jwtProvider.getTokenID(dto.getAccessToken());
        }
        if (fromUserId != 0){
            Boolean checkIsLike = myPageService.checkTeamLike(fromUserId, teamID);
            return new ResponseEntity<>(checkIsLike, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @GetMapping("/community/islike/{communityID}")
    public ResponseEntity<?> communityLike(@RequestHeader (value = "Authorization", required = false)String token, @PathVariable long communityID) throws Exception{
        Long fromUserId = Long.valueOf(0);
        if (!jwtProvider.extractToken(token).equals("null")) {
            TokenResDto dto = jwtProvider.tokenManager(token);
            fromUserId = jwtProvider.getTokenID(dto.getAccessToken());
        }
        if (fromUserId != 0){
            Boolean checkIsLike = myPageService.checkCoummintyLike(fromUserId, communityID);
            return new ResponseEntity<>(checkIsLike, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }



}
