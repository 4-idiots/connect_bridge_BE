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
        public String createAccessToken(Long id, String uID, String uName){

            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            header.put("token_type","access token");

            Map<String,Object> payloads = new HashMap<>();
            payloads.put("id",id);
            payloads.put("userID",uID);
            payloads.put("userName",uName);

            Long expiredTime = 1000 * 10L; // valiedTime(2h)

            Date now = new Date();

            return Jwts.builder()
                    .setHeader(header)
                    .setClaims(payloads)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + expiredTime)) // 토큰 만료 시간
                    .signWith(SignatureAlgorithm.HS256,secretKey.getBytes()) // HS256과 key로 Sign
                    .compact();
        }

        public String createRefreshToken(){
            Long expiredTime = 1000 * 40L ;

            Date now = new Date();

            return Jwts.builder()
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + expiredTime))
                    .signWith(SignatureAlgorithm.HS256,secretKey.getBytes()) // HS256과 key로 Sign
                    .compact();
        }


    public Map<String,Object> verifyJWT(String jwt) throws UnsupportedEncodingException{
        Map<String,Object> claimMap = null;
        try{

            claimMap = Jwts.parser()
                    .setSigningKey(secretKey.getBytes("UTF-8"))
                    .parseClaimsJws(jwt) // 파싱 검증
                    .getBody();

        } catch (ExpiredJwtException e){
            System.out.println(e);
        } catch (Exception e){
            System.out.println(e);
        }

        return claimMap;
    }

}