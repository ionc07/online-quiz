package com.online.quiz.security;

import com.online.quiz.model.User;
import com.online.quiz.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
  private final UserRepository userRepository;

  private final JwtTokenUtil jwtTokenUtil;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                UserRepository userRepository,
                                JwtTokenUtil jwtTokenUtil) {
    super(authenticationManager);
    this.userRepository = userRepository;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    // Read the Authorization header, where the JWT token should be
    String header = request.getHeader(JwtProperties.HEADER_STRING);

    // If header does not contain BEARER or is null delegate to Spring impl and exit
    if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    String token = request.getHeader(JwtProperties.HEADER_STRING)
            .replace(JwtProperties.TOKEN_PREFIX, "");

    // if the token is going to expire then add to the response header a new one
    if (jwtTokenUtil.isGoingToExpire(token)) {
      String email = jwtTokenUtil.decodeToken(token).getSubject();
      String newToken = jwtTokenUtil.createToken(email);
      response.addHeader("Authorization", newToken);
    }

    // If header is present, try grab user principal from database and perform authorization
    Authentication authentication = getUsernamePasswordAuthentication(request);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Continue filter execution
    chain.doFilter(request, response);
  }

  private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
    String token = request.getHeader(JwtProperties.HEADER_STRING)
            .replace(JwtProperties.TOKEN_PREFIX, "");

    if (token != null) {
      // parse the token and validate it
      String email = jwtTokenUtil.decodeToken(token)
              .getSubject();

      // Search in the DB if we find the user by token subject (email)
      // If so, then grab user details and create spring auth token using email, pass, authorities/roles
      if (email != null) {
        User user = userRepository.findByEmail(email);
        UserPrincipal principal = new UserPrincipal(user);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, principal.getAuthorities());
        return auth;
      }
      return null;
    }
    return null;
  }

}
