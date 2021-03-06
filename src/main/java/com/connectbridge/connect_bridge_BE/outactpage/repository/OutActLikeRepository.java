package com.connectbridge.connect_bridge_BE.outactpage.repository;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutAct;
import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutActLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutActLikeRepository extends JpaRepository<OutActLike,Long> {
    //Long countByUserIDAndOutActID(Long userID, Long outActID);
    OutActLike findByUserIDAndOutActID(Long userID, Long outActID);
    Long countByOutActID(Long outActID);
    boolean existsByUserIDAndOutActID(Long userID, Long outActID);

    //List<OutAct> findIdByUserIDOrderByIdDesc(long fromUserId);
}
