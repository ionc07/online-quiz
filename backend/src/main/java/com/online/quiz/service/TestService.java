package com.online.quiz.service;

import com.online.quiz.dto.TestDTO;

import java.util.List;

public interface TestService {
  void create(TestDTO testDTO);

  List<TestDTO> getAllTests();
}
