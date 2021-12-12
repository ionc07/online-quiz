package com.online.quiz.service;

import com.online.quiz.dto.AuthRequest;
import com.online.quiz.dto.CreateUserRequest;

public interface AuthService {
  String authenticate(AuthRequest authRequest);

  void register(CreateUserRequest createUserRequest);
}
