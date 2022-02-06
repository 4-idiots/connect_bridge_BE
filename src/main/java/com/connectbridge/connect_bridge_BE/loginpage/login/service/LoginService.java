package com.connectbridge.connect_bridge_BE.loginpage.login.service;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.LoginDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.LoginEntity;

public interface LoginService {

    LoginEntity login(LoginDto dto) throws Exception;
}
