package com.connectbridge.connect_bridge_BE.loginpage.register.data.entity;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.EmailCodeDto;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@Table(name = "mailcode")
public class EmailCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "useremail")
    private String userEmail;
    @Column(name = "code")
    private String code;

    public void updateEmail(String userEmail, String code){
        this.userEmail = userEmail;
        this.code = code;
    }
}
