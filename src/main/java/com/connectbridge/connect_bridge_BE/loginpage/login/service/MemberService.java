package com.connectbridge.connect_bridge_BE.loginpage.login.service;

import com.connectbridge.connect_bridge_BE.loginpage.login.config.CustomUserDetail;
import com.connectbridge.connect_bridge_BE.loginpage.login.domain.entity.MemberEntity;
import com.connectbridge.connect_bridge_BE.loginpage.login.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Logging이 필요하지 않을까? 뭐 하나 볼때마다 필요한게 5개씩 나오냐.

//@Transational ,Logging(log4j)에 대한 학습이 필요함.
//javax랑 springframework에 있는거랑 뭐가다른지.
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // @Transactional: Atomicity, Consistency, Isolation, Durability
    // 적용된 범위에서 트랜잭션 기능이 포함된 프록시 객체가 생성되어 자동 commit, rollback 진행
    @Transactional
    public void joinUser(MemberEntity memberEntity){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberEntity.setUserPW(passwordEncoder.encode(memberEntity.getUserPW()));
        memberRepository.saveMember(memberEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findUserByUserID(username);
        return new CustomUserDetail(memberEntity);
    }
}