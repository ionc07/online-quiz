package com.online.quiz.security;

import com.online.quiz.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

  private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
  private final String jwtIssuer = "example.io";

  public String generateAccessToken(User user) {
    return Jwts.builder()
            .setSubject(format("%s,%s", user.getId(), user.getEmail()))
            .setIssuer(jwtIssuer)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }

  public String getUserId(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

    return claims.getSubject().split(",")[0];
  }

  public String getEmail(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

    return claims.getSubject().split(",")[1];
  }

  public Date getExpirationDate(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

    return claims.getExpiration();
  }

  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
//    } catch (SignatureException ex) {
//    } catch (MalformedJwtException ex) {
//    } catch (ExpiredJwtException ex) {
//    } catch (UnsupportedJwtException ex) {
//    } catch (IllegalArgumentException ex) {
//    }
//    return false;
  }

}
