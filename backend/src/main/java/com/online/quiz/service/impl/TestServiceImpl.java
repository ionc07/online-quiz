package com.online.quiz.service.impl;

import com.online.quiz.dto.TestDTO;
import com.online.quiz.model.Test;
import com.online.quiz.model.TestSettings;
import com.online.quiz.model.mapper.Mapper;
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

  private final Mapper<Test, TestDTO> testDtoMapper;

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
  public List<TestDTO> getAllTests() {
    List<Test> tests = testRepository.findAllBy();

    return testDtoMapper.mapList(tests);
  }

}
