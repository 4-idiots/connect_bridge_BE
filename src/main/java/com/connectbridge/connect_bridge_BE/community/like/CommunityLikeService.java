package com.connectbridge.connect_bridge_BE.community.like;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.community.CommunityRepository;
import com.connectbridge.connect_bridge_BE.follow.Follow;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommunityLikeService {
    private final CommunityLikeRepository communityLikeRepository;
    private final CommunityRepository communityRepository;
    private final RegisterRepository registerRepository;


    public boolean likeChk(Long fromUserId, Long toPostId) {
        if (null == communityLikeRepository.findByFromUserIdAndToPostId(fromUserId, toPostId)) {
            return true;
        } else {
            return false;
        }
    }

    public void likeOff(Long fromUserId, Long toPostId){
        CommunityLike communityLike = communityLikeRepository.findByFromUserIdAndToPostId(fromUserId, toPostId);
        communityLikeRepository.deleteById(communityLike.getId());
        likecounting(toPostId);
    }

    public void likeOn(Long fromUserId, Long toPostId){
        CommunityLike communityLike = new CommunityLike().createCommunityLike(fromUserId, toPostId);
        communityLikeRepository.save(communityLike);
        likecounting(toPostId);
    }
    @Transactional
    public void likecounting(long toPostId) {
        CommunityEntity communityEntity = communityRepository.findByid(toPostId);
        int likecount = communityLikeRepository.findCommunityLikeCountById(toPostId);
        communityEntity.likecountup(toPostId, likecount);
        communityRepository.save(communityEntity);
    }
}
