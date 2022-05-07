package com.connectbridge.connect_bridge_BE.loginpage.login.controller;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

    private final JwtProvider jwtProvider;

    public TokenController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/valid")
    public ResponseEntity<TokenResDto> tokenChk(@RequestHeader("Authorization") String token){
        try {
            TokenResDto chk = jwtProvider.tokenManager(token);

            if (chk.getAccessToken().equals("Err")) {

                return ResponseEntity.badRequest().build();
            }

            return new ResponseEntity<TokenResDto>(chk, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);// try catch 추가됬음.
        }
    }
}
