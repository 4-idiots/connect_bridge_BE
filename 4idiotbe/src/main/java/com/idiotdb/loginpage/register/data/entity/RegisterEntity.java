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
    private Long id;

    @Column(nullable = false)
    private String user_phone;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String user_pw;

    public RegisterDto toregisterDto() {
        return RegisterDto.builder()
                .ID(id)
                .userPhone(user_phone)
                .userID(user_id)
                .userPW(user_pw)
                .build();
    }
}
