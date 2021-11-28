package com.online.quiz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.quiz.dto.UserLoginDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  private JwtTokenUtil jwtTokenUtil;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  /* Trigger when we issue POST request to /login
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
          throws AuthenticationException {

    // Grab credentials and map them to login viewmodel
    UserLoginDTO credentials = null;
    try {
      credentials = new ObjectMapper().readValue(request.getInputStream(), UserLoginDTO.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Create login token
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            credentials.getEmail(),
            credentials.getPassword(),
            new ArrayList<>());

    // Authenticate user
    Authentication auth = authenticationManager.authenticate(authenticationToken);

    return auth;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {
    // Grab principal
    UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

    // Create JWT Token
    String token = jwtTokenUtil.createToken(principal.getUsername());

    // Add token in response
    response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);

    response.getWriter().write("{\"role\": \"" + principal.getAuthorities().toArray()[0].toString() + "\"}");
    response.getWriter().flush();
    response.getWriter().close();
  }
}
