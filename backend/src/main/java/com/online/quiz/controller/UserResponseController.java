package com.online.quiz.controller;

import com.online.quiz.dto.TestAndUserResponseDTO;
import com.online.quiz.model.UserResponse;
import com.online.quiz.service.UserResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-responses")
@RequiredArgsConstructor
public class UserResponseController {

  private final UserResponseService userResponseService;

  @GetMapping
  public ResponseEntity<List<UserResponse>> getUserResponsesByTestId(@RequestParam Long testId) {
    return ResponseEntity.ok(userResponseService.getUserResponseByTestId(testId));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<TestAndUserResponseDTO> getUserResponseForUserAndTestId(@PathVariable Long userId, @RequestParam Long testId) {
    return ResponseEntity.ok(userResponseService.getUserResponseForUserAndTestId(userId, testId));
  }

  @PostMapping
  public ResponseEntity<?> createUserResponse(@RequestBody List<UserResponse> userResponses, @RequestParam Long testId) {
    userResponseService.saveUserResponse(testId, userResponses);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUserResponse(@PathVariable Long id) {
    userResponseService.deleteUserResponse(id);
    return ResponseEntity.noContent().build();
  }
}
