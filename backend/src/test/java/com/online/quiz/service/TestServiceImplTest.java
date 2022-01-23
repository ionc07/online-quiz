package com.online.quiz.service;

import com.online.quiz.dto.AnswerDTO;
import com.online.quiz.dto.QuestionDTO;
import com.online.quiz.dto.TestDTO;
import com.online.quiz.dto.TestSettingsDTO;
import com.online.quiz.model.mapper.AnswerToDtoMapper;
import com.online.quiz.model.mapper.QuestionToDtoMapper;
import com.online.quiz.model.mapper.TestSettingsToDtoMapper;
import com.online.quiz.model.mapper.TestToDtoMapper;
import com.online.quiz.repository.TestRepository;
import com.online.quiz.service.impl.TestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestServiceImplTest {
  @Mock
  private TestRepository testRepository;

  private TestService testService;

  private List<TestDTO> tests;

  @BeforeEach
  public void setUp() {
    TestToDtoMapper testToDtoMapper = new TestToDtoMapper(new QuestionToDtoMapper(new AnswerToDtoMapper()), new TestSettingsToDtoMapper());
    testService = new TestServiceImpl(testRepository, testToDtoMapper);

    TestDTO testDTO = TestDTO.builder()
            .title("First test")
            .description("This is a test description")
            .settings(TestSettingsDTO.builder()
                              .timeLimit(60)
                              .maxAttempts(1)
                              .chatEnabled(false)
                              .build())
            .questions(List.of(QuestionDTO.builder()
                                       .waitForTimeLimit(false)
                                       .value("What's the capital of Denmark?")
                                       .sequence(0)
                                       .score(1)
                                       .answers(List.of(AnswerDTO.builder()
                                                                .value("Copenhagen")
                                                                .correct(true)
                                                                .score(1)
                                                                .answerType("Radio button")
                                                                .build()))
                                       .build()))
            .build();

    List<TestDTO> testDTOS = new ArrayList<>();
    testDTOS.add(testDTO);

    com.online.quiz.model.Test test = new com.online.quiz.model.Test();

  }

}
