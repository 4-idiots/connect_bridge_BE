package com.connectbridge.connect_bridge_BE.loginpage.register.service;


import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.*;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.EmailCode;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.EmailCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;
    private final EmailCodeRepository emailCodeRepository;

    private MimeMessage createMessage(String to)throws Exception{ //2
        String ePw = createKey();
        EmailCodeDto emailCodeDto = new EmailCodeDto();
        EmailCode emailCode = emailCodeRepository.findByuserEmail(to);
        emailCodeDto.setUserEmail(to);
        emailCodeDto.setCode(ePw);
        log.info("to {}", emailCodeDto);
        boolean checkingEmail =  emailCodeRepository.existsByuserEmail(to);
        if(!checkingEmail){
            emailCodeRepository.save(emailCodeDto.togetEmailCode());
        }else{
            emailCode.updateEmail(to, ePw);
            emailCodeRepository.save(emailCode);
        }
        MimeMessage  message = emailSender.createMimeMessage();

        String code = ePw;
        message.addRecipients(Message.RecipientType.TO, to); //보내는 대상
        message.setSubject("C & B 확인 코드: " + code); //제목

        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 창이 있는 브라우저 창에 입력하세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += code;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html"); //내용
        message.setFrom(new InternetAddress("ktjrghks0914@gmail.com","C & B")); //보내는 사람

        return message;
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public void sendSimpleMessage(String to) throws Exception { //1
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
    public boolean checkCode(String getcode, String getemail) {
        EmailCode checking = emailCodeRepository.findByuserEmailAndCode(getemail, getcode);
        System.out.println("checking 값 : " + checking);
        if(checking == null){
            return false;
        }
        else {
            emailCodeRepository.deleteByCode(getcode);
            return true;
        }

    }
}
