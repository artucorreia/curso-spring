package com.example.firstproject.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.firstproject.data.DTO.v1.TokenDTO;
import com.example.firstproject.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class TokenService {

    @Value("${security.jwt.token.secret}")
    private String secretKey;

    public TokenDTO generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Instant generateIssueDate = generateIssueDate();
            Instant generateExpirationDate = generateExpirationDate(1);
            String token = JWT.create().withIssuer("first-project")
                    .withSubject(user.getLogin())
                    .withIssuedAt(generateIssueDate)
                    .withExpiresAt(generateExpirationDate)
                    .sign(algorithm);

            return new TokenDTO(user.getLogin(), token, generateIssueDate, generateExpirationDate);
        }
        catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating a token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("first-project")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant generateExpirationDate(Integer durationHour) {
        return LocalDateTime.now().plusHours(durationHour).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant generateIssueDate() {
        ZoneId brazilZone = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime brazilDateTime = ZonedDateTime.now(brazilZone);
        return brazilDateTime.toInstant();
    }
}
