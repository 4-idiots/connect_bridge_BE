package com.connectbridge.connect_bridge_BE.loginpage.login.controller;

import com.connectbridge.connect_bridge_BE.security.config.CustomUserDetail;
import com.connectbridge.connect_bridge_BE.loginpage.login.domain.entity.MemberEntity;
import com.connectbridge.connect_bridge_BE.loginpage.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    //회원가입 폼
    @GetMapping("/signUp")//우리꺼 주소로 변경
    public String signUpForm(){
        return "signup";
    }


    //회원가입 진행
    @PostMapping("/signUp")
    public String signUp(MemberEntity memberEntity){
        memberEntity.setRole("USER");
        memberService.joinUser(memberEntity);
        log.info(memberEntity.getUserID());
        return "redirect:/login";
    }

    @GetMapping("/")
    public String userAccess(Model model, Authentication authentication){
        CustomUserDetail customUserDetail = (CustomUserDetail)authentication.getPrincipal();
        log.info(customUserDetail.getUsername());
        model.addAttribute("info",customUserDetail.getUsername());
        return "user_access";
    }
}