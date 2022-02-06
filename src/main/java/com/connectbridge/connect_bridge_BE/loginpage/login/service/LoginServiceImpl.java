package com.connectbridge.connect_bridge_BE.loginpage.login.service;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.LoginDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.LoginEntity;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public LoginEntity login(LoginDto dto) throws Exception {
        return null;
    }
}
