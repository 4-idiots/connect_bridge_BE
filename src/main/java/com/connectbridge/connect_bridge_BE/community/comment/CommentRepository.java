package com.connectbridge.connect_bridge_BE.community.comment;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;




public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    //void deleteCommentsByPost(CommunityEntity communityEntity);
}
