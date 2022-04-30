package com.connectbridge.connect_bridge_BE.like;

import com.connectbridge.connect_bridge_BE.like.entity.OutActLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutActLikeRepository extends JpaRepository<OutActLike,Long> {
    Long countByUserIDAndOutActID(Long userID, Long outActID);
    OutActLike findByUserIDAndOutActID(Long userID, Long outActID);
    Long countByOutActID(Long outActID);
}
