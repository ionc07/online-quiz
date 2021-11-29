package com.online.quiz.service;

import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.dto.UserUpdateDTO;
import com.online.quiz.model.User;
import com.online.quiz.projection.UserDetails;

public interface UserService {
  void save(User user);

  void verify(String email);

  void resetPassword(UserResetPasswordDTO userResetPasswordDTO);

  void update(UserUpdateDTO userUpdateDTO);

  UserDetails getCurrentUser();

  void delete(Long id);
}
