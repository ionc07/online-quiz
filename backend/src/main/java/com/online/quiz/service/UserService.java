package com.online.quiz.service;

import com.online.quiz.dto.*;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
  void verify(String email);

  void resetPassword(UserResetPasswordDTO userResetPasswordDTO);

  void update(UserUpdateDTO userUpdateDTO);

  void shareTestsWithUsers(TestsSharedWithUsersDTO testsSharedWithUsersDTO);

  User getCurrentUser();

  UserShortDetailsDTO getUserByEmail(String email);

  List<UserShortDetailsDTO> getAllUsersByUserGroup(Long userGroupId);

  PaginationDTO<UserDTO> getAll(Pageable pageable);

  void delete(Long id);

  User findUserByEmail(String email);

  String getCurrentUserEmail();
}
