package com.online.quiz.service;

import com.online.quiz.dto.UserDTO;
import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.dto.UserUpdateDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.User;
import com.online.quiz.projection.UserDetails;
import org.springframework.data.domain.Pageable;

public interface UserService {
  void verify(String email);

  void resetPassword(UserResetPasswordDTO userResetPasswordDTO);

  void update(UserUpdateDTO userUpdateDTO);

  UserDetails getCurrentUser();

  PaginationDTO<UserDTO> getAll(Pageable pageable);

  void delete(Long id);

  User findUserByEmail(String email);

  String getCurrentUserEmail();
}
