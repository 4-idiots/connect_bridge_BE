package com.connectbridge.connect_bridge_BE.community.like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    CommunityLike findByFromUserIdAndToPostId(long from_user_id, long to_post_id);
}
