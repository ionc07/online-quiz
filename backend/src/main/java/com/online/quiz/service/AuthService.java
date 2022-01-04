package com.online.quiz.service;

import com.online.quiz.dto.AuthDTO;
import com.online.quiz.dto.CreateUserDTO;

public interface AuthService {
  String authenticate(AuthDTO authDTO);

  void register(CreateUserDTO createUserDTO);
}
