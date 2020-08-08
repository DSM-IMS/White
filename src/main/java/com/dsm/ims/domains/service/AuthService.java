package com.dsm.ims.domains.service;

import com.dsm.ims.domains.domain.User;
import com.dsm.ims.domains.repository.UserRepository;
import com.dsm.ims.exception.IdMismatchException;
import com.dsm.ims.exception.PasswordMismatchException;
import com.dsm.ims.exception.RefreshTokenMismatchException;
import com.dsm.ims.exception.TokenExpirationException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Transactional
public class AuthService {
    private static final String ALGORITHM = "SHA-512";
    private static final String ENCODING = "UTF-8";

    private UserRepository userRepository;
    private JwtService jwtService;

    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String login(User user) throws PasswordMismatchException, IdMismatchException {
        String userId = user.getId();
        String userPw = sha512(user.getPw());

        User findUser = userRepository.findById(userId);

        if (findUser == null)
            throw new IdMismatchException();
        String findUserPw = findUser.getPw();

        if (!(userPw.equals(findUserPw)))
            throw new PasswordMismatchException();

        String accessToken = jwtService.createAccessToken(userId);
        String refreshToken = jwtService.createRefreshToken(userId);
        LocalDateTime expiration = LocalDateTime.ofInstant(jwtService.getExpiration(accessToken).toInstant(), ZoneId.of("Asia/Seoul"));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", accessToken);
        jsonObject.put("refreshToken", refreshToken);
        jsonObject.put("expiration", expiration);

        return jsonObject.toJSONString();
    }

    private String sha512(String original) {
        String resultHex = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.reset();
            digest.update(original.getBytes(ENCODING));
            resultHex = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch(Exception e) {
            System.out.println("존재하지 않는 인코딩 : " + ENCODING);
            System.out.println("존재하지 않는 암호화 : " + ALGORITHM);
        }
        System.out.println(resultHex);
        return resultHex;
    }

    public String accessTokenReissuance(String refreshToken) {
        User findUser = null;
        if(jwtService.isValid(refreshToken) && jwtService.isTimeOut(refreshToken))
            findUser = userRepository.findByRefreshToken(refreshToken);
        else
            throw new TokenExpirationException();

        String accessToken = null;
        if(refreshToken.equals(findUser.getRefreshToken()))
            accessToken = jwtService.createAccessToken(findUser.getId());
        else
            throw new RefreshTokenMismatchException();

        return accessToken;
    }

    public void join(User user) {
        user.setPw(sha512(user.getPw()));
        userRepository.save(user);
    }
}
