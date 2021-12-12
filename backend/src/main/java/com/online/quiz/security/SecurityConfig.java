package com.online.quiz.security;

import com.online.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static java.lang.String.format;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserRepository userRepository;
  private final JwtTokenFilter jwtTokenFilter;

  @Value("${springdoc.api-docs.path}")
  private String restApiDocPath;
  @Value("${springdoc.swagger-ui.path}")
  private String swaggerPath;

  public SecurityConfig(UserRepository userRepository,
                        JwtTokenFilter jwtTokenFilter) {
    super();

    this.userRepository = userRepository;
    this.jwtTokenFilter = jwtTokenFilter;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(email -> userRepository
            .findByEmail(email)
            .orElseThrow(
                    () -> new UsernameNotFoundException(
                            format("User: %s, not found", email)
                    )
            ));
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(format("%s/**", restApiDocPath)).permitAll()
            .antMatchers(format("%s/**", swaggerPath)).permitAll()

            .anyRequest().permitAll();

    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
