package com.online.quiz.service;

import com.online.quiz.dto.TestDTO;
import com.online.quiz.model.Test;

import java.util.List;

public interface TestService {
  void create(TestDTO testDTO);

  List<Test> getAllTests();
}
