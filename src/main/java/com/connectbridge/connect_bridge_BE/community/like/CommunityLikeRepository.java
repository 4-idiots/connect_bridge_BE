package com.connectbridge.connect_bridge_BE.community.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    CommunityLike findByFromUserIdAndToPostId(long from_user_id, long to_post_id);

    @Transactional
    @Query(value = "SELECT COUNT(*) FROM communitylike WHERE to_post_id = :postId", nativeQuery = true)
    int findCommunityLikeCountById(@Param("postId")long postId);
}
