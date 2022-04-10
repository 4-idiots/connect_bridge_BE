package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.community.like.CommunityLikeRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final RegisterRepository registerRepository;
    private final CommunityLikeRepository communityLikeRepository;


    @Transactional
    public void save(CommunityDto communityDto){
        RegisterEntity id = registerRepository.findById(communityDto.getFromUserId()).get();
        String nickname = id.getUserNickname();
        communityDto.setUserNickname(nickname);
        communityRepository.save(communityDto.communityEntity()).getId();
    }

    public List<CommunityDto> getList(Pageable pageable, int reqPage) {

        pageable = PageRequest.of(reqPage, 2, Sort.by(Sort.Direction.DESC, "id"));

        Page<CommunityEntity> page = communityRepository.findAll(pageable); // DB값 불러옴.

        List<CommunityDto> pageDto = page.map(CommunityDto::new).getContent(); // List로 받게 바꿔봄 ㅋ

        System.out.println("Service getList 동작 됨" + page);

        return pageDto;
    }
    @Transactional
    public CommunityDto getCommunityPage(long fromUserId, long communityID){
        CommunityDto communityDto = new CommunityDto();

        CommunityEntity communityEntity = communityRepository.getById(communityID);
        communityDto.setPostID(communityEntity.getId());
        communityDto.setTitle(communityEntity.getTitle());
        communityDto.setHashtag(communityDto.convertList(communityEntity.getHashtag()));
        communityDto.setContents(communityEntity.getContents());
        communityDto.setUserNickname(communityEntity.getUserNickname());
        communityDto.setViewCount(communityEntity.getViewCount());
        communityDto.setLikeCount(communityEntity.getLikeCount());
        communityDto.setCommentCount(communityEntity.getCommentCount());
        if (fromUserId != 0){
            if (communityLikeRepository.findByFromUserIdAndToPostId(fromUserId, communityID) != null){
                communityDto.setState(Long.valueOf(2));
                communityDto.setColor("danger"); //좋아요함
            }else {communityDto.setState(Long.valueOf(1));
                communityDto.setColor("black");
                } //좋아요 안함
        }else{
            communityDto.setState(Long.valueOf(3)); //로그인 안한 사람
        }

        return communityDto;
    }

}
