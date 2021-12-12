package com.online.quiz.service.impl;

import com.online.quiz.dto.AuthRequest;
import com.online.quiz.dto.CreateUserRequest;
import com.online.quiz.exception.UserAlreadyExistsException;
import com.online.quiz.mail.EmailService;
import com.online.quiz.model.User;
import com.online.quiz.repository.RoleRepository;
import com.online.quiz.repository.UserRepository;
import com.online.quiz.security.JwtTokenUtil;
import com.online.quiz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final JwtTokenUtil jwtTokenUtil;

  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final EmailService emailService;

  private final PasswordEncoder passwordEncoder;

  @Override
  public String authenticate(AuthRequest authRequest) {
    userRepository.findByEmail(authRequest.getEmail()).ifPresent(t -> {
      throw new UserAlreadyExistsException("This email is already taken.");
    });
    Authentication authenticate = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

    User user = (User) authenticate.getPrincipal();

    return jwtTokenUtil.generateAccessToken(user);
  }

  @Override
  public void register(CreateUserRequest createUserRequest) {
    User user = new User();
    user.setFirstName(createUserRequest.getFirstName());
    user.setLastName(createUserRequest.getLastName());
    user.setEmail(createUserRequest.getEmail());
    user.setRole(roleRepository.findByName("ROLE_user"));
    user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
    userRepository.save(user);
    emailService.sendMessage(createUserRequest.getEmail(), "Welcome", "Hello and welcome to online quiz platform!s");
  }
}
