package com.connectbridge.connect_bridge_BE.follow;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "from_user_id")
    private Long fromUserId;


    @Column(name = "to_user_id")
    private Long toUserId;



    public Follow createFollowLike(Long fromUserId, Long toUserId) {
        return Follow.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .build();
    }
}