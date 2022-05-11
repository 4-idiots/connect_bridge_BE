package com.connectbridge.connect_bridge_BE.community.like;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    CommunityLike findByFromUserIdAndToPostId(long from_user_id, long to_post_id);

    //void deleteLikesBytoPost(CommunityEntity communityEntity);

    @Transactional
    @Query(value = "SELECT COUNT(*) FROM communitylike WHERE to_post_id = :postId", nativeQuery = true)
    int findCommunityLikeCountById(@Param("postId")long postId);

    //List<CommunityEntity> findByUserIDOrderByIdDesc(long fromUserId);
}
