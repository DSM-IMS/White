package com.dsm.ims.domains.service;

import com.dsm.ims.domains.domain.User;
import com.dsm.ims.domains.repository.UserRepository;
import com.dsm.ims.exception.IdMismatchException;
import com.dsm.ims.exception.PasswordMismatchException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

@Service
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
        // String userPw = sha512(user.getPw());
        String userPw = user.getPw();

        User findUser = userRepository.findById(userId);

        if (findUser == null)
            throw new IdMismatchException();
        String findUserPw = findUser.getPw();

        if (!(userPw.equals(findUserPw)))
            throw new PasswordMismatchException();

        String accessToken = jwtService.createAccessToken(userId);
        String refreshToken = jwtService.createRefreshToken(userId);
        //Date expiration = jwtService.getExpiration(userId);

        JSONObject jsonObject = new JSONObject();
        

        return "";
    }

    private String sha512(String original) {
        String resultHex = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(original.getBytes(ENCODING));
            resultHex = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch(Exception e) {
            System.out.println("존재하지 않는 인코딩 : " + ENCODING);
            System.out.println("존재하지 않는 암호화 : " + ALGORITHM);
        }
        return resultHex;
    }
}
