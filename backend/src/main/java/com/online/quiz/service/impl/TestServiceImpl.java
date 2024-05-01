package com.online.quiz.service.impl;

import com.online.quiz.dto.*;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.*;
import com.online.quiz.model.mapper.Mapper;
import com.online.quiz.projection.UserDetails;
import com.online.quiz.repository.AnswerRepository;
import com.online.quiz.repository.QuestionRepository;
import com.online.quiz.repository.TestRepository;
import com.online.quiz.repository.TestSettingsRepository;
import com.online.quiz.service.TestService;
import com.online.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private final TestRepository testRepository;
  private final TestSettingsRepository testSettingsRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  private final Mapper<Test, TestShortDetailsDTO> testShortDetailsDTOMapper;
  private final Mapper<Test, TestDTO> testToDTOMapper;

  private final UserService userService;

  @Transactional
  @Override
  public void create(TestDTO testDTO) {
    // Test settings mapping
    TestSettingsDTO testSettingsDTO = testDTO.getSettings();

    TestSettings testSettings = new TestSettings();

    if (testSettingsDTO.getAvailableFrom() != null) {
      testSettings.setAvailableFrom(LocalDateTime.parse(testSettingsDTO.getAvailableFrom(), testToDTOMapper.getFormatter()));
    }
    if (testSettingsDTO.getAvailableTo() != null) {
      testSettings.setAvailableTo(LocalDateTime.parse(testSettingsDTO.getAvailableTo(), testToDTOMapper.getFormatter()));
    }
    testSettings.setChatEnabled(testSettingsDTO.getChatEnabled());
    testSettingsDTO.setMaxAttempts(testSettingsDTO.getMaxAttempts());

    if (testSettingsDTO.getMaxAttempts() != null) {
      testSettings.setTimeLimit(testSettingsDTO.getTimeLimit());
    }

    testSettings = testSettingsRepository.save(testSettings);

    // General info test mapping
    Test test = new Test();
    test.setUser(userService.findUserByEmail(userService.getCurrentUserEmail()));
    test.setDescription(testDTO.getDescription());
    test.setTitle(testDTO.getTitle());
    test.setSettings(testSettings);
    test = testRepository.save(test);

    // Questions mapping
    for (QuestionDTO questionDTO : testDTO.getQuestions()) {
      Question question = new Question();

      AnswerType answerType = new AnswerType();
      answerType.setId(1L);

      question.setAnswerType(answerType);
      question.setValue(questionDTO.getValue());
      question.setScore(questionDTO.getScore());
      question.setSequence(questionDTO.getSequence());
      question.setTimeLimit(questionDTO.getTimeLimit());
      question.setWaitForTimeLimit(questionDTO.getWaitForTimeLimit());
      question.setTest(test);

      questionRepository.save(question);

      // Answers of a question mapping
      for (AnswerDTO answerDTO : questionDTO.getAnswers()) {
        Answer answer = new Answer();
        answer.setScore(answerDTO.getScore());
        answer.setValue(answerDTO.getValue());
        answer.setCorrect(answerDTO.getCorrect());

        answer.setQuestion(question);

        answerRepository.save(answer);
      }
    }
  }

  @Override
  public PaginationDTO<TestShortDetailsDTO> getAllTests(Pageable pageable) {
    Page<Test> testsPage = testRepository.findAllBy(pageable);

    return new PaginationDTO<>(testShortDetailsDTOMapper.mapList(testsPage.getContent()), testsPage);
  }

  @Override
  public PaginationDTO<TestShortDetailsDTO> getTestsForCurrentUser(Pageable pageable) {
    UserDetails currentUser = userService.getCurrentUser();
    Page<Test> testsPage = testRepository.findAllByUserId(pageable, currentUser.getId());

    return new PaginationDTO<>(testShortDetailsDTOMapper.mapList(testsPage.getContent()), testsPage);
  }

  @Override
  public TestDTO getTest(Long id) {
    Test test = testRepository.findTestById(id);

    return testToDTOMapper.map(test);
  }

}
