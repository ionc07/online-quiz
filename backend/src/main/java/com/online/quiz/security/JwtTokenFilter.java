package com.online.quiz.security;

import com.online.quiz.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.List.of;
import static java.util.Optional.ofNullable;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;
  private final UserRepository userRepository;

  public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws ServletException, IOException {
    // Get authorization header and validate
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (isEmpty(header) || !header.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    // Get jwt token and validate
    final String token = header.split(" ")[1].trim();
    if (!jwtTokenUtil.validate(token)) {
      chain.doFilter(request, response);
      return;
    }

    // Get user identity and set it on the spring security context
    UserDetails userDetails = userRepository
            .findByEmail(jwtTokenUtil.getEmail(token)).orElseThrow();

    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null,
            ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(of())
    );

    authentication
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }
}
