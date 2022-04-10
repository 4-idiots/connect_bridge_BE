package com.connectbridge.connect_bridge_BE.community.like;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.community.CommunityRepository;
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

    @Transactional
    public long unLike(long fromUserId, long toPostId) {
        CommunityLike communityLike = communityLikeRepository.findByFromUserIdAndToPostId(fromUserId, toPostId);
        if(communityLike != null) return communityLike.getId();
        else return -1;
    }

    @Transactional
    public void save(Long fromUserId, Long toPostId){
        RegisterEntity fromUser = registerRepository.findById(fromUserId).get();
        CommunityEntity topost = communityRepository.findById(toPostId).get();

        communityLikeRepository.save(
                CommunityLike.builder()
                        .fromUser(fromUser)
                        .toPost(topost)
                        .build());
    }
}
