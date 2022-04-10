package com.connectbridge.connect_bridge_BE.loginpage.register.repository;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.EmailCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmailCodeRepository extends JpaRepository<EmailCode, Long> {
    EmailCode findByuserEmail(String userEmail);
    EmailCode findByuserEmailAndCode(String userEmail, String Code);
    boolean existsByuserEmail(String userEmail);
    @Transactional
    void deleteByCode(String code);
}
