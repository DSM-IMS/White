package com.dsm.ims.service;

import com.dsm.ims.util.UnauthorizedException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtService {

    private static final String SECURE_KEY = "dhwlddjgmanf";
    private static final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECURE_KEY);
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static final Key KEY = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    public String createAccessToken(String userId){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("access token")
                .claim("id", userId)
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 30)))             // 30분
                .signWith(signatureAlgorithm, KEY)
                .compact();
    }

    public String createRefreshToken(String userId) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("reflash token")
                .claim("id", userId)
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)))    // 2주
                .signWith(signatureAlgorithm, KEY)
                .compact();
    }

    public Date getExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public boolean isValid(String jwt) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(jwt);

            return true;
        } catch(Exception e) {
            throw new UnauthorizedException();
        }
    }

    public boolean isTimeOut(String jwt) {
        try {
            Date now = new Date();
            Date expiration = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getExpiration();

            if(expiration.after(now)) {
                return true;
            }

            return false;
        } catch(Exception e) {
            return false;
        }
    }
}
