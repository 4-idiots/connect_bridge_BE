package com.connectbridge.connect_bridge_BE.follow;


import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final RegisterRepository registerRepository;

    @Transactional
    public long unFollow(long fromUserId, long toUserId) {
        Follow follow = followRepository.findFollowByFromUserIdAndToUserId(fromUserId, toUserId);
        if(follow != null) return follow.getId();
        else return -1;
    }

    @Transactional
    public void save(Long fromUserId, Long toUserId){
        RegisterEntity fromUser = registerRepository.findById(fromUserId).get();
        RegisterEntity toUser = registerRepository.findById(toUserId).get();

        followRepository.save(
                Follow.builder()
                        .fromUser(fromUser)
                        .toUser(toUser)
                        .build());
    }
}
