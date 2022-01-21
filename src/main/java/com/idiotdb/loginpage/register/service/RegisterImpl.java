package com.idiotdb.loginpage.register.service;

import com.idiotdb.loginpage.register.data.dto.RegisterDto;
import com.idiotdb.loginpage.register.data.entity.RegisterEntity;
import com.idiotdb.loginpage.register.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RegisterImpl implements RegisterService{

    private final RegisterRepository repository;

    @Override
    public List<RegisterDto> getRegisters() throws Exception {
        List<RegisterEntity> entityList = repository.findAll();
        List<RegisterDto> dtoList = new ArrayList<>();
        for (RegisterEntity entity : entityList){
            dtoList.add(entity.toregisterDto());
        }
        return dtoList;
    }

    @Override
    @Transactional
    public Long postRegister(RegisterDto registerDto) throws Exception {
        return repository.save(registerDto.toregisterEntity()).getId();
    }



}
