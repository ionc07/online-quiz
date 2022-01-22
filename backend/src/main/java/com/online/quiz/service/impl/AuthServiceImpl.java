package com.online.quiz.service.impl;

import com.online.quiz.dto.AuthDTO;
import com.online.quiz.dto.CreateUserDTO;
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

import java.util.HashMap;
import java.util.Map;

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
  public Map<String, String> authenticate(AuthDTO authDTO) {
    try {
      Authentication authenticate = authenticationManager
              .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
      User user = (User) authenticate.getPrincipal();

      Map<String, String> tokenAndRole = new HashMap<>();
      tokenAndRole.put("token", jwtTokenUtil.generateAccessToken(user));
      tokenAndRole.put("role", user.getRole().getName());

      return tokenAndRole;

    } catch (Exception e) {
      throw new UserAlreadyExistsException("Bad credentials.");
    }

  }

  @Override
  public void register(CreateUserDTO createUserDTO) {
    userRepository.findByEmail(createUserDTO.getEmail()).ifPresent(t -> {
      throw new UserAlreadyExistsException("This email is already taken.");
    });
    // TODO
    // user mapper
    User user = new User();
    user.setFirstName(createUserDTO.getFirstName());
    user.setLastName(createUserDTO.getLastName());
    user.setEmail(createUserDTO.getEmail());
    user.setRole(roleRepository.findByName("ROLE_user"));
    user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
    userRepository.save(user);
    emailService.sendMessage(createUserDTO.getEmail(), "Welcome", "Hello and welcome to online quiz platform!");
  }
}
