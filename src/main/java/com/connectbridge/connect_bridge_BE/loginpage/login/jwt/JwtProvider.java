package com.connectbridge.connect_bridge_BE.loginpage.login.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final String secretKey = "secret";

    public String createToken(Long id, String uID, String uName){

        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");

        Map<String,Object> payloads = new HashMap<>();
        payloads.put("id",id);
        payloads.put("userID",uID);
        payloads.put("userName",uName);
        payloads.put("message","ok");

        Long expiredTime = 1000 * 60L * 60L * 2L; // valiedTime(2h)

        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        String jwt = Jwts.builder()
                .setHeader(header)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS256,secretKey.getBytes()) // HS256과 key로 Sign
                .compact();// 토큰 생성성

        return jwt;
    }

    public Map<String,Object> verifyJWT(String jwt) throws UnsupportedEncodingException{
        Map<String,Object> claimMap = null;
        try{
            Claims claims =Jwts.parser()
                    .setSigningKey(secretKey.getBytes("UTF-8"))
                    .parseClaimsJws(jwt) // 파싱 검증
                    .getBody();

            claimMap = claims;
        } catch (ExpiredJwtException e){
            System.out.println(e);
        }

        return claimMap;
    }

}
