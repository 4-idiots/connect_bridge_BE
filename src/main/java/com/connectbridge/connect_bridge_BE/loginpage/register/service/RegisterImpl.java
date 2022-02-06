package com.connectbridge.connect_bridge_BE.loginpage.register.service;

import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RegisterImpl implements RegisterService{

    private final RegisterRepository registerRepository;


    @Override
    public List<RegisterDto> getRegisters() throws Exception {
        List<RegisterEntity> entityList = registerRepository.findAll();
        List<RegisterDto> dtoList = new ArrayList<>();
        for (RegisterEntity entity : entityList){
            dtoList.add(entity.toregisterDto());
        }
        return dtoList;
    }

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
        return registerRepository.save(registerDto.toregisterEntity()).getId();
    }
}
