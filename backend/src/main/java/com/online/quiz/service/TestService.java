package com.online.quiz.service;

import com.online.quiz.model.Test;

import java.util.List;

public interface TestService {
  void create(Test test);

  List<Test> getAllTests();
}
