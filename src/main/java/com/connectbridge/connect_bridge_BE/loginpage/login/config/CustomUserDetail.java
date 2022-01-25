package com.connectbridge.connect_bridge_BE.loginpage.login.config;


import com.connectbridge.connect_bridge_BE.loginpage.login.domain.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class CustomUserDetail implements UserDetails {
    private String userID;
    private String userPW;
    private String userEmail;
    private String auth;

    public CustomUserDetail(MemberEntity memberEntity){
        this.userID = memberEntity.getUserID(); // 사용자의 id를 가져온다.
        this.userPW = memberEntity.getUserPW(); // 사용자의 pw를 가져온다.
        this.userEmail = memberEntity.getUserEmail(); // 사용자의 email을 가져온다.
        this.auth = "ROLE_" + memberEntity.getRole(); // 사용자의 권한을 가져온다.
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        // 계정이 가지고 있는 권한 목록을 반환한다. -> USER
        return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
    }
    @Override
    public String getUsername(){ //UserDetail.getUsername()
        return this.userID;
    }

    @Override
    public String getPassword(){ //UserDetail.getPassword()
        return this.userPW;
    }
    
    @Override
    public boolean isAccountNonExpired() { // 계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 잠김 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //계정 비밀번호 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled() { //계정 활성화 여부
        return true;
    }
}
