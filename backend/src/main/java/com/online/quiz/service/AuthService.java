package com.online.quiz.service;

import com.online.quiz.dto.AuthDTO;
import com.online.quiz.dto.CreateUserDTO;

import java.util.Map;

public interface AuthService {
  Map<String, String> authenticate(AuthDTO authDTO);

  void register(CreateUserDTO createUserDTO);
}
