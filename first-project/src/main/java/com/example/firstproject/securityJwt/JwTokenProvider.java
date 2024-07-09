package com.example.firstproject.securityJwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.firstproject.data.DTO.v1.security.TokenDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class JwTokenProvider implements Serializable {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAccessToken(String username, List<String> roles) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validity = now.plusSeconds(validityInMilliseconds/1000);
        String accessToken = getAccessToken(username, roles, now, validity);
        String refreshToken = getAccessToken(username, roles, now);

        return new TokenDTO(
                username,
                true,
                now,
                validity,
                accessToken,
                refreshToken
        );
    }

    private String getAccessToken(String username, List<String> roles, LocalDateTime now, LocalDateTime validity) {
        return null;
    }

    private String getAccessToken(String username, List<String> roles, LocalDateTime now) {
        return null;
    }
}
