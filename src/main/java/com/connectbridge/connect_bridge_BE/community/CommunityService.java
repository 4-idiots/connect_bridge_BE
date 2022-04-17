package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.community.like.CommunityLikeRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Slf4j
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final RegisterRepository registerRepository;
    private final CommunityLikeRepository communityLikeRepository;

    public CommunityService(CommunityRepository communityRepository, RegisterRepository registerRepository, CommunityLikeRepository communityLikeRepository){
        this.communityRepository = communityRepository;
        this.registerRepository = registerRepository;
        this.communityLikeRepository = communityLikeRepository;
    }


    @Transactional
    public void save(CommunityDto communityDto){
        RegisterEntity id = registerRepository.findById(communityDto.getFromUserId()).get();
        String nickname = id.getUserNickname();
        communityDto.setUserNickname(nickname);
        communityDto.setViewCount(0);
        communityDto.setLikeCount(0);
        communityDto.setCommentCount(0);
        communityRepository.save(communityDto.communityEntity()).getId();
    }

    public List<CommunityDto> getList(Pageable pageable, int reqPage) {

        pageable = PageRequest.of(reqPage, 2, Sort.by(Sort.Direction.DESC, "id"));

        Page<CommunityEntity> page = communityRepository.findAll(pageable); // DB값 불러옴.

        List<CommunityDto> pageDto = page.map(CommunityDto::new).getContent(); // List로 받게 바꿔봄 ㅋ

        System.out.println("Service getList 동작 됨" + page);

        return pageDto;
    }
    public void postcountup(long communityID){
        CommunityEntity community = communityRepository.findByid(communityID);
        long viewCount = community.getViewCount();
        viewCount += 1;
        community.postcountup(communityID, viewCount);
        communityRepository.save(community);
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
        communityDto.setLikeCounta(communityEntity.getLikeCount());
        communityDto.setCommentCount(communityEntity.getCommentCount());
        communityDto.setCommentList(communityEntity.getCommentList());
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

    public void updateCommunity(CommunityDto communityDto) throws Exception{
        CommunityEntity community = communityRepository.findByid(communityDto.getPostID());
        community.updateCommunity(communityDto.getPostID(), communityDto.getTitle(),
                communityDto.getContents(), communityDto.convertStr(communityDto.getHashtag()));
        communityRepository.save(community);
    }

}
