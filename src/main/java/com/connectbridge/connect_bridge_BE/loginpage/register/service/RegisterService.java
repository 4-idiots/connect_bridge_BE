package com.connectbridge.connect_bridge_BE.loginpage.register.service;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.TeamProfileDto;

import java.util.List;

public interface RegisterService {
    TeamProfileDto getTeamProfile(Long id) throws Exception;

    Boolean checkNicknameDuplicate(String userNickname) throws Exception;

    Boolean checkIdDuplicate(String userID) throws Exception;

    Boolean checkEmailDuplicate(String userEmail) throws Exception;

    Long postRegister(RegisterDto registerDto) throws Exception;

    List<RegisterDto> getRegisters() throws Exception;

}
