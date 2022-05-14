package com.connectbridge.connect_bridge_BE.loginpage.register.service;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RegisterImpl implements RegisterService{

    private final RegisterRepository registerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean checkNicknameDuplicate(String userNickname) throws Exception{
        Boolean checknickname = registerRepository.existsByuserNickname(userNickname);
        return checknickname;
    }
    @Override
    public Boolean checkIdDuplicate(String userID) throws Exception{
        Boolean checkId = registerRepository.existsByuserID(userID);
        return checkId;
    }
    @Override
    public Boolean checkEmailDuplicate(String userEmail) throws Exception{
        Boolean checkEmail = registerRepository.existsByuserEmail(userEmail);
        return checkEmail;
    }

    @Override
    @Transactional
    public Long postRegister(RegisterDto registerDto) throws Exception{
        String encodePassword = passwordEncoder.encode(registerDto.getUserPW());
        registerDto.setUserPW(encodePassword);
        registerDto.setUserPicture("https://cnbimg.s3.ap-northeast-2.amazonaws.com/etc/cnb+team+logo.svg");
        return registerRepository.save(registerDto.toregisterEntity()).getId();
    }


}
