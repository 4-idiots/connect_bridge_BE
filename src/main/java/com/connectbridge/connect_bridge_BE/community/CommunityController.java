package com.connectbridge.connect_bridge_BE.community;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class CommunityController {
    private final CommunityRepository communityRepository;
    private final CommunityService communityService;

    @GetMapping("/communityw")
    public ResponseEntity<String> getcommunitywrite() throws Exception{
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @GetMapping("/communitychange/{communityID}")
    public ResponseEntity<?> getcommunityinfo(@PathVariable long communityID){
        CommunityChangeDto getcommunityfix = communityService.getCommunityFixPage(communityID);
        return new ResponseEntity<>(getcommunityfix,HttpStatus.OK);
    }

    @PostMapping("/write/{fromUserId}")
    public ResponseEntity<?> communityWrite(@RequestBody CommunityCreateDto communityCreateDto,@PathVariable long fromUserId) throws Exception{
        communityCreateDto.setFromUserId(fromUserId);
        communityService.save(communityCreateDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping("/community")
    public ResponseEntity<?> getPageList(){
        return new ResponseEntity<>(communityService.getList(),HttpStatus.OK);
    }

    @GetMapping("/community/info/{fromUserId}/{communityID}")
    public ResponseEntity<?> community(@PathVariable long fromUserId, @PathVariable long communityID) throws Exception{
        communityService.postcountup(communityID);   //여기에 선방영으로 post카운터 올리고 +1

        CommunityDto getcommunitypage = communityService.getCommunityPage(fromUserId, communityID);
        return ResponseEntity.ok(getcommunitypage);
    }
    @PatchMapping("/community/write")
    public ResponseEntity<?> communityPatch(@RequestBody CommunityCreateDto communityCreateDto) throws Exception{
        System.out.println(communityCreateDto.getContents() + communityCreateDto.getTitle() + communityCreateDto.getPostID());
        communityService.updateCommunity(communityCreateDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @DeleteMapping("/community/{communityId}")
    public ResponseEntity communityDel(@PathVariable long communityId) {
        communityService.communityDelete(communityId);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/community/popular")
    public ResponseEntity<?> getPopularCommunity(){
        return new ResponseEntity<>(communityService.getPopularPost(),HttpStatus.OK);
    }
    @GetMapping("/serach/{keyword}")
    public ResponseEntity<?> getSerach(@PathVariable("keyword") String keyword){
        return new ResponseEntity<>(communityService.getSerach(keyword), HttpStatus.OK);
    }

}
