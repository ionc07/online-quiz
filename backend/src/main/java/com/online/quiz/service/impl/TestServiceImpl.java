package com.online.quiz.service.impl;

import com.online.quiz.dto.TestDTO;
import com.online.quiz.model.Question;
import com.online.quiz.model.Test;
import com.online.quiz.model.TestSettings;
import com.online.quiz.model.User;
import com.online.quiz.repository.TestRepository;
import com.online.quiz.service.TestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private final TestRepository testRepository;

  @Override
  public void create(TestDTO testDTO) {
    ModelMapper modelMapper = new ModelMapper();
    // testRepository.save(test);
    Test test = modelMapper.map(testDTO, Test.class);
    test.setQuestions(null);
    test.setSettings(null);

    TestSettings testSettings = modelMapper.map(test.getSettings(), TestSettings.class);

//    testSettings.set
  }

  @Override
  public List<Test> getAllTests() {
    return testRepository.findAllBy();
  }

}
