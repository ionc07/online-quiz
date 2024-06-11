package com.online.quiz.repository;

import com.online.quiz.model.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
  List<UserResponse> findAllByTestId(Long testId);

  List<UserResponse> findAllByUserIdAndTestId(Long userId, Long testId);
}