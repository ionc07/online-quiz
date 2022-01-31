package com.online.quiz.service.impl;

import com.online.quiz.dto.AnswerDTO;
import com.online.quiz.dto.QuestionDTO;
import com.online.quiz.dto.TestDTO;
import com.online.quiz.dto.TestSettingsDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.Answer;
import com.online.quiz.model.AnswerType;
import com.online.quiz.model.Question;
import com.online.quiz.model.Test;
import com.online.quiz.model.TestSettings;
import com.online.quiz.model.mapper.Mapper;
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

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private final TestRepository testRepository;
  private final TestSettingsRepository testSettingsRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  private final Mapper<Test, TestDTO> testDtoMapper;

  private final UserService userService;

  @Transactional
  @Override
  public void create(TestDTO testDTO) {
    Test test = new Test();
    // General info test mapping
    test.setUser(userService.findUserByEmail(userService.getCurrentUserEmail()));
    test.setDescription(testDTO.getDescription());
    test.setTitle(testDTO.getTitle());
    test = testRepository.save(test);

    Long testId = test.getId();

    // Test settings mapping
    TestSettingsDTO testSettingsDTO = testDTO.getSettings();

    TestSettings testSettings = new TestSettings();

    testSettings.setAvailableFrom(testSettingsDTO.getAvailableFrom());
    testSettings.setAvailableTo(testSettingsDTO.getAvailableTo());
    testSettings.setChatEnabled(testSettingsDTO.getChatEnabled());
    testSettingsDTO.setMaxAttempts(testSettingsDTO.getMaxAttempts());
    testSettings.setTimeLimit(testSettingsDTO.getTimeLimit());

    testSettings = testSettingsRepository.save(testSettings);

    test.setSettings(testSettings);
    test = testRepository.save(test);

    // Questions mapping
    for (QuestionDTO questionDTO : testDTO.getQuestions()) {
      Question question = new Question();

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

        AnswerType answerType = new AnswerType();
        answerType.setId(1L);

        answer.setAnswerType(answerType);
        answer.setQuestion(question);

        answerRepository.save(answer);
      }
    }
  }

  @Override
  public PaginationDTO<TestDTO> getAllTests(Pageable pageable) {
    Page<Test> testsPage = testRepository.findAllBy(pageable);
    PaginationDTO<TestDTO> paginationDTO = new PaginationDTO<>(testDtoMapper.mapList(testsPage.getContent()), testsPage);

    return paginationDTO;
  }

}
