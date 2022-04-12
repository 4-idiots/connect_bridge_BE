package com.connectbridge.connect_bridge_BE.community;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.Message;
import com.connectbridge.connect_bridge_BE.teampage.TeamProfileDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CommunityController {
    private final CommunityRepository communityRepository;
    private final CommunityService communityService;

    @GetMapping("/community")
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


    @GetMapping("/community{page}")
    public ResponseEntity<List<CommunityDto>> getPageList(@PathVariable("page") int page, Pageable pageable){
        System.out.println("페이지 번호" + page);
        List<CommunityDto> list = communityService.getList(pageable, page);
        if(list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return ResponseEntity.ok(list);
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




}
