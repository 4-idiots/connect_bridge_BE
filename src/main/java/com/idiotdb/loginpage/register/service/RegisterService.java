package com.idiotdb.loginpage.register.service;

import com.idiotdb.loginpage.register.data.dto.RegisterDto;

import java.util.List;

public interface RegisterService {

    List<RegisterDto> getRegisters() throws Exception;

    Long postRegister(RegisterDto registerDto) throws Exception;
}
