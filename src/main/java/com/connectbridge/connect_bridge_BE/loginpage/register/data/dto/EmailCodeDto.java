package com.connectbridge.connect_bridge_BE.loginpage.register.data.dto;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.EmailCode;
import lombok.*;
@Data
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
public class EmailCodeDto {
    private Long id;
    private String userEmail;
    private String code;

    public EmailCode togetEmailCode() {
        return  EmailCode.builder()
                .userEmail(userEmail)
                .code(code)
                .build();
    }

}