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
                        name="Likepost",
                        columnNames = {"from_user_id", "to_post"}
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

    @JoinColumn(name = "to_post")
    @ManyToOne
    private RegisterEntity toPost;

    @Builder
    public Like(RegisterEntity fromUser, RegisterEntity toPost) {
        this.fromUser = fromUser;
        this.toPost = toPost;
    }
}
