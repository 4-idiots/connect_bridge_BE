package com.idiotdb.loginpage.register.data.dto;

import com.idiotdb.loginpage.register.data.entity.RegisterEntity;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class RegisterDto {
    private Long ID;
    private String userPhone;
    private String userID;
    private String userPW;

    public RegisterEntity toregisterEntity() {
        return RegisterEntity.builder()
                .id(ID)
                .user_phone(userPhone)
                .user_id(userID)
                .user_pw(userPW)
                .build();
    }
}
