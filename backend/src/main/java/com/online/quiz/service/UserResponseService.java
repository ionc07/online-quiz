package com.online.quiz.service;

import com.online.quiz.dto.TestAndUserResponseDTO;
import com.online.quiz.model.UserResponse;

import java.util.List;

public interface UserResponseService {

  List<UserResponse> getAllUserResponses();

  UserResponse getUserResponseById(Long id);

  TestAndUserResponseDTO getUserResponseForUserAndTestId(Long userId, Long testId);

  List<UserResponse> getUserResponseByTestId(Long testId);

  void saveUserResponse(Long testId, List<UserResponse> userResponses);

  void deleteUserResponse(Long id);
}
