package com.connectbridge.connect_bridge_BE.community;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import com.connectbridge.connect_bridge_BE.teampage.TeamProfileDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CommunityController {
    private final CommunityRepository communityRepository;
    private final CommunityService communityService;

    @GetMapping("/communityw")
    public ResponseEntity<String> getcommunitywrite() throws Exception{
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/write/{fromUserId}")
    public ResponseEntity<?> communityWrite(@RequestBody CommunityDto communityDto,@PathVariable long fromUserId) throws Exception{
        communityDto.setFromUserId(fromUserId);
        communityService.save(communityDto);
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
    public ResponseEntity<?> communityPatch(@RequestBody CommunityDto communityDto) throws Exception{
        communityService.updateCommunity(communityDto);
        Message message = Message.builder()
                .message("ok")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @DeleteMapping("/community/delete/{id}")
    public ResponseEntity communityDel(@PathVariable long communityId) {
        //communityService.communityDelete(communityId);
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
