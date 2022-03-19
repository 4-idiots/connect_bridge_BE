package com.connectbridge.connect_bridge_BE.likes;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="subscribe_uk",
                        columnNames = {"from_user_id", "to_user_id"}
                )
        }
)

public class Like {

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
    public Like(RegisterEntity fromUser, RegisterEntity toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
