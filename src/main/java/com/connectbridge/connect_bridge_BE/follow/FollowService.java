package com.connectbridge.connect_bridge_BE.follow;


import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectLikeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;

    public boolean likeChk(Long fromUserId, Long toUserId) {
        if (null == followRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)) {
            return true;
        } else {
            return false;
        }
    }

    public void likeOff(Long fromUserId, Long toUserId){
        Follow followLike = followRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);
        followRepository.deleteById(followLike.getId());
    }

    public void likeOn(Long fromUserId, Long toUserId){
        Follow followLike = new Follow().createFollowLike(fromUserId, toUserId);
        followRepository.save(followLike);
    }
}
