package com.idiotdb.loginpage.register.data.entity;

import com.idiotdb.loginpage.register.data.dto.RegisterDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")

public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    public RegisterDto toregisterDto() {
        return RegisterDto.builder()
                .ID(ID)
                .userPhone(userPhone)
                .userId(userId)
                .userPw(userPw)
                .build();
    }
}
