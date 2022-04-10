package com.connectbridge.connect_bridge_BE.follow;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private RegisterEntity fromUser;

    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private RegisterEntity toUser;

    @Builder
    public Follow(RegisterEntity fromUser, RegisterEntity toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

}