package com.connectbridge.connect_bridge_BE.community;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class CommunityController {
    private final CommunityService communityService;
    private final JwtProvider jwtProvider;

    @GetMapping("/communityw") //커뮤니티 글쓰기 get
    public ResponseEntity<String> getcommunitywrite() throws Exception{
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @GetMapping("/communitychange/{communityID}") //커뮤니티 수정 get
    public ResponseEntity<?> getcommunityinfo(@PathVariable long communityID){
        CommunityChangeDto getcommunityfix = communityService.getCommunityFixPage(communityID);
        return new ResponseEntity<>(getcommunityfix,HttpStatus.OK);
    }

    @PostMapping("/write") //커뮤니티 생성 글쓰기 post
    public ResponseEntity<?> communityWrite(@RequestBody CommunityCreateDto communityCreateDto,@RequestHeader (value = "Authorization", required = false)String token) throws Exception{
        TokenResDto tokenResDto = jwtProvider.tokenManager(token);
        Long fromUserId = jwtProvider.getTokenID(tokenResDto.getAccessToken());
        communityCreateDto.setFromUserId(fromUserId);
        communityService.save(communityCreateDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping("/community") //커뮤니티 get
    public ResponseEntity<?> getPageList(){
        return new ResponseEntity<>(communityService.getList(),HttpStatus.OK);
    }

    @GetMapping("/community/info/{communityID}") //커뮤니티 상세 get
    public ResponseEntity<?> community(@PathVariable long communityID, @RequestHeader (value = "Authorization", required = false)String token) throws Exception{
        Long fromUserId = Long.valueOf(0);
        if (!jwtProvider.extractToken(token).equals("null")) {
            TokenResDto dto = jwtProvider.tokenManager(token);
            fromUserId = jwtProvider.getTokenID(dto.getAccessToken());
        }
        communityService.postcountup(communityID);   //여기에 선방영으로 post카운터 올리고 +1
        CommunityDto getcommunitypage = communityService.getCommunityPage(fromUserId, communityID);
        return ResponseEntity.ok(getcommunitypage);
    }
    @PatchMapping("/community/write") //커뮤니티 수정 patch
    public ResponseEntity<?> communityPatch(@RequestBody CommunityCreateDto communityCreateDto) throws Exception{
        System.out.println(communityCreateDto.getContents() + communityCreateDto.getTitle() + communityCreateDto.getPostID());
        communityService.updateCommunity(communityCreateDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @DeleteMapping("/community/{communityId}") //커뮤니티 삭제
    public ResponseEntity communityDel(@PathVariable long communityId) {
        communityService.communityDelete(communityId);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/community/popular") //인기글
    public ResponseEntity<?> getPopularCommunity(){
        return new ResponseEntity<>(communityService.getPopularPost(),HttpStatus.OK);
    }
    @GetMapping("/serach/{keyword}") //검색
    public ResponseEntity<?> getSerach(@PathVariable("keyword") String keyword){
        return new ResponseEntity<>(communityService.getSerach(keyword), HttpStatus.OK);
    }

}
