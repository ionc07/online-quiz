package com.online.quiz.service.impl;

import com.online.quiz.model.Test;
import com.online.quiz.repository.TestRepository;
import com.online.quiz.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private final TestRepository testRepository;

  @Override
  public void create(Test test) {
    testRepository.save(test);
  }

  @Override
  public List<Test> getAllTests() {
    return testRepository.findAll();
  }

}
