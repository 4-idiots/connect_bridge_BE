package com.connectbridge.connect_bridge_BE.loginpage.register.data.dto;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter

public class RegisterDto {
    private Long ID;
    @Pattern(regexp="^[\\d]{10,11}",
            message="휴대폰 번호는 필수 입력 값입니다.")
    private String userPhone;
    @Pattern(regexp="^[A-Za-z\\d]{5,20}",
            message="아이디는 필수 입력 값입니다.")
    private String userID;
    @NotBlank(message="비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="^[A-Za-z\\d~!@#$%^&*()+|=]{8,20}",
            message = "비밀번호는 영문, 숫자, 특수기호가 1개 이상씩 포함된 8자 ~ 15자의 비밀번호여야 합니다.")
    private String userPW;
    @NotBlank(message="닉네임은 필수 입력 값입니다.")
    private String userNickname;
    @NotBlank(message="이름은 필수 입력 값입니다.")
    private String userName;
    private String userBirthday;
    private Boolean userGender;

    @NotBlank(message="이메일은 필수 입력 값입니다.")
    @Email(message="이메일 형식에 맞지 않습니다.")
    private String userEmail;
    private String userAbility;
    private String userArea;
    private String userTime;
    private String userInterest;
    private String userIntroduce;


    public RegisterEntity toregisterEntity() {
        return RegisterEntity.builder()
                .id(ID)
                .userPhone(userPhone)
                .userID(userID)
                .userPW(userPW)
                .userNickname(userNickname)
                .userName(userName)
                .userBirthday(userBirthday)
                .userGender(userGender)
                .userEmail(userEmail)
                .userAbility(userAbility)
                .userArea(userArea)
                .userTime(userTime)
                .userInterest(userInterest)
                .userIntroduce(userIntroduce)
                .build();
    }
}
