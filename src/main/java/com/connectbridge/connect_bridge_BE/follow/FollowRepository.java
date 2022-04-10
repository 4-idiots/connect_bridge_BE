package com.connectbridge.connect_bridge_BE.follow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findFollowByFromUserIdAndToUserId(long from_user_id, long to_user_id);
    //Boolean findFollowByFromUserIdAndId(long from_user_id, long to_user_id);


}
