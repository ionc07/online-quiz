package com.online.quiz.service;

import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.model.User;

public interface UserService {
  void save(User user);

  void verify(String email);

  void updatePassword(UserResetPasswordDTO userResetPasswordDTO);
}
