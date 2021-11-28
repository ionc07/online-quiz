package com.online.quiz.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtTokenUtil {

  DecodedJWT decodeToken(String token) {
    return JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
            .build()
            .verify(token);
  }

  String createToken(String subject) {
    return JWT.create()
            .withSubject(subject)
            .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
            .sign(HMAC512(JwtProperties.SECRET.getBytes()));
  }

  boolean isGoingToExpire(String token) {

    Date tokenExpireAt = decodeToken(token).getExpiresAt();

    long diffInMilliseconds = Math.abs(new Date().getTime() - tokenExpireAt.getTime());
    long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);

    return diffInDays <= JwtProperties.REFRESH_BEFORE_EXPIRING_DAYS;
  }
}
