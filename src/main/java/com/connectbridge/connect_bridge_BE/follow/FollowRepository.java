package com.connectbridge.connect_bridge_BE.follow;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByFromUserIdAndToUserId(long fromUserId, long toUserId);
    Boolean existsByFromUserIdAndToUserId(long fromUserId, long teamID);
}
