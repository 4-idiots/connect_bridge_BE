package com.connectbridge.connect_bridge_BE.community.comment;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    //void deleteCommentsBycommunity(CommunityEntity communityEntity);

    @Transactional
    @Query(value = "SELECT COUNT(*) FROM comment WHERE post_id = :postId", nativeQuery = true)
    int findCommentCountById(@Param("postId")long postId);
}
