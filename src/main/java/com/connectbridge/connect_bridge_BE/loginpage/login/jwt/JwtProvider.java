package com.connectbridge.connect_bridge_BE.loginpage.login.jwt;

import com.connectbridge.connect_bridge_BE.loginpage.login.data.dto.TokenResDto;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtProvider {

    private final UserRepository userRepository;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Autowired
    public JwtProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // access token 발급
    public String createAccessToken(Long id, String userID, String userName) {

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", id);
        payloads.put("userID", userID);
        payloads.put("userName", userName);

        Date now = new Date();

        // 2h
        //long ACCESS_TOKEN_VALID_TIME = 10 * 60 * 60 * 1000L;
        long ACCESS_TOKEN_VALID_TIME = 1000L*2000;

        JwtBuilder builder = Jwts.builder()
                .setHeader(header)
                .setClaims(payloads)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // 토큰 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()); // HS256과 key로 Sign

        return builder.compact();
    }

    // refresh token 발급
    public String createRefreshToken() {

        Date now = new Date();

        // 2주
        //long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L;
        long REFRESH_TOKEN_VALID_TIME =1000L*40;


        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // HS256과 key로 Sign
                .compact();
    }

    // 토큰 만료 검증.
    public boolean validateToken(String token) {
        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes()) // 서명된 key를 불러와야함.
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // 토큰 재발급 기능.
    public TokenResDto tokenManager(String accessT){

        validationHeader(accessT);
        String accessToken = extractToken(accessT);

        String inputToken = accessToken; //Bearer 넣는다고 치면 이걸 때서 줄까 그냥 줄까?
        Claims claims = null;

        if(!validateToken(accessToken)) {

            try {
                // Token parser

                claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(accessToken).getBody();
            }catch (ExpiredJwtException e) {


                claims = e.getClaims();

                // access 생성을 위한 추출
                int id = (int) claims.get("id");
                String uID = (String) claims.get("userID");
                String uName = (String) claims.get("userName");

                // token 정보 확인후 refreshToken 검증
                User user = userRepository.findByIdAndUserIDAndAndUserName((long) id, uID, uName);
                String chkRefToken = user.getRefreshToken();

                // access 재발급.
                if (!validateToken(chkRefToken)) {
                    return new TokenResDto("Err");
                }
                inputToken = createAccessToken((long) id, uID, uName);

        }catch (Exception e){
                System.out.println(e);
                return new TokenResDto("Err");
            }
        }
        return new TokenResDto(inputToken);
    }

    // 앞에 Bearer 있는지 확인
    private void validationHeader(String header){
        if(header == null || !header.startsWith("Bearer")){
            throw new IllegalArgumentException();
        }
    }

    // Bearer 분리
    private String extractToken(String authHeader){
        return authHeader.substring("Bearer ".length());
    }

}