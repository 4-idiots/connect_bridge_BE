package com.connectbridge.connect_bridge_BE.loginpage.register.service;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;

import java.util.List;

public interface RegisterService {

    List<RegisterDto> getRegisters() throws Exception;

    Long postRegister(RegisterDto registerDto) throws Exception;
}
