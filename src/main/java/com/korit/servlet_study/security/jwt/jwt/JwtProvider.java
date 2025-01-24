package com.korit.servlet_study.security.jwt.jwt;

import com.korit.servlet_study.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtProvider {
    private Key key;
    private static JwtProvider instance;

    private JwtProvider() {

        // https://jwtsecret.com/generate 에서 키값을 가져온다.(바뀌지 않기 때문에 final로 지정)
        final String SECRET = "3fbab317f390576eeb26e517a8b04a3554165ca8d7e7f1e31226653c7daa3c5a34a3e4881614f7d895eadd6a98808f9c540e48d81991377fafac1339d57a3deb3254804e7ec3788e44e5dfa2047a4ac6f9fde8b159d42486248ac2c83020872237a4a47b1340bf11fff404dcfc457390dae38eee6c718ca258361c2d692c64f403909c2758d6806ba5b57f23c94a924162b956cc3bda4164dd98d9c1dc5b3b0cda92a430d068e5f3a9f390ca4edce6b26705204a12b73eca7e723c4e021f54b09787963d8ac25cff83d90cc14792d22db89cab74217a36449ef321dc482e34e6e1c4c4a11994f56364dea1ae71b37bd405843f75ae423d84e069649d2bbca89b";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)); // 위의 키값을 decoders를 가지고 key값 생성
    }

    public static JwtProvider getInstance() {
        if (instance == null) {
            instance = new JwtProvider();
        }
        return instance;
    }

    private Date getExpiryDate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 365)); // 현재 시간 + 1년
    }

    // 토큰 생성
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId())
                .setExpiration(getExpiryDate()) // 만료시간 set
                .signWith(key, SignatureAlgorithm.HS256) // 키를 만들때 256으로 만들었기 때문에 HS256을 사용한다.
                .compact(); //.build()와 같다.
    }

    public Claims parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(removeBearer(token))
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    private String removeBearer(String bearerToken) {
        String accessToken = null;
        final String BEARER_KEYWORD = "Bearer ";
        if (bearerToken.startsWith(BEARER_KEYWORD)) {
            accessToken = bearerToken.substring(BEARER_KEYWORD.length());
        }
        return accessToken;
    }
}
