package com.connectbridge.connect_bridge_BE.loginpage.login.service;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.MailDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendEmailService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private JavaMailSender mailSender;

    public MailDto createMailAndChangePassword(String userName, String userEmail){

        String str = getTempPassword();

        MailDto dto = new MailDto();
        dto.setImplPw(str);
        dto.setUserEmail(userEmail);
        dto.setTitle("CNB 임시 비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. CNB 임시비밀번호 안내 이메일 입니다." + userName + " 님의 임시 비밀번호는 " + str + "입니다.");

        return dto;
    }
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z' };

        StringBuffer tempPw = new StringBuffer();

        for(int i = 1; i<10;i++){
            int idx = (int)(charSet.length * Math.random());
            tempPw.append(charSet[idx]);
        }

        return tempPw.toString();
    }

    public void mailSend(MailDto mailDto){
        System.out.println("이메일 전송");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getUserEmail());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }

}
