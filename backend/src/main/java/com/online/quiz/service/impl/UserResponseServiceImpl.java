package com.online.quiz.service.impl;

import com.online.quiz.dto.TestAndUserResponseDTO;
import com.online.quiz.dto.TestDTO;
import com.online.quiz.dto.UserResponseDTO;
import com.online.quiz.model.Test;
import com.online.quiz.model.User;
import com.online.quiz.model.UserResponse;
import com.online.quiz.repository.UserResponseRepository;
import com.online.quiz.service.TestService;
import com.online.quiz.service.UserResponseService;
import com.online.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserResponseServiceImpl implements UserResponseService {

  private final UserResponseRepository userResponseRepository;
  private final UserService userService;
  private final TestService testService;

  public List<UserResponse> getAllUserResponses() {
    return userResponseRepository.findAll();
  }

  public UserResponse getUserResponseById(Long id) {
    return userResponseRepository.findById(id).orElse(null);
  }

  @Override
  public TestAndUserResponseDTO getUserResponseForUserAndTestId(Long userId, Long testId) {
    TestDTO testDTO = testService.getTest(testId);
    List<UserResponse> userResponses = userResponseRepository.findAllByUserIdAndTestId(userId, testId);

    // Group responses by questionId and collect answerIds
    Map<Long, List<Long>> responsesGroupedByQuestion = userResponses.stream()
            .collect(Collectors.groupingBy(
                    userResponse -> userResponse.getQuestion().getId(),
                    Collectors.mapping(userResponse -> userResponse.getAnswer().getId(), Collectors.toList())
            ));

    // Create UserResponseDTO list
    List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
    for (Map.Entry<Long, List<Long>> entry : responsesGroupedByQuestion.entrySet()) {
      UserResponseDTO userResponseDTO = UserResponseDTO.builder()
              .questionId(entry.getKey())
              .selectedAnswerIds(entry.getValue())
              .build();
      userResponseDTOS.add(userResponseDTO);
    }

    return TestAndUserResponseDTO.builder()
            .test(testDTO)
            .userResponses(userResponseDTOS)
            .build();
  }

  @Override
  public List<UserResponse> getUserResponseByTestId(Long testId) {
    return userResponseRepository.findAllByTestId(testId);
  }

  public void saveUserResponse(Long testId, List<UserResponse> userResponses) {
    User currentUser = userService.getCurrentUser();
    Test currentTest = new Test(testId);
    userResponses.forEach(userResponse -> {
      userResponse.setTest(currentTest);
      userResponse.setUser(currentUser);
      userResponseRepository.save(userResponse);
    });

  }

  public void deleteUserResponse(Long id) {
    userResponseRepository.deleteById(id);
  }
}