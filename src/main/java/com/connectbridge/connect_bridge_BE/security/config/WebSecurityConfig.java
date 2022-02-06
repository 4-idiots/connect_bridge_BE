package com.connectbridge.connect_bridge_BE.security.config;

import com.connectbridge.connect_bridge_BE.loginpage.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MemberService memberService;

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/vss/**","/script/**","/js/**","/html/**");
    }

    //HttpSecurity 객체를 이용하여 요청을 intercept함. URL인증, login, logout처리를 한다.
    protected void configure(HttpSecurity http) throws  Exception{
        http.authorizeRequests()
                .antMatchers("/user","/user/register", "/user/check/userNickname","/user/check/userID","/user/check/userEmail", "/login","/register","/LoginPage/FindID","/LoginPage/FindPW").permitAll()
                .antMatchers("/").hasRole("USER") //URL요청에 대한 접근 여부 설정
                .anyRequest().authenticated()
                .and()
                .formLogin() // 로그인 설정
                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공시 연결되는 주소
                .and()
                .logout() // 로그아웃 설정
                .logoutSuccessUrl("/login") // 로그아웃 성공시 연결 주소
                .invalidateHttpSession(true) //로그아웃 성공시 저장된 세션 날림.
                .and()
                .csrf().disable()
                ;
        ;
    }

    @Override
    //인증 객체 생성 memberService는 UserDetails를 상속받아 로그인 된 사용자의 데이터를 관리한다.
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        //passwordEncoder(): 로그인에서 입력한 패스워드를 암호화 처리한다.
        auth.userDetailsService(memberService).passwordEncoder(new BCryptPasswordEncoder()); // BCrypt 사용
    }
}