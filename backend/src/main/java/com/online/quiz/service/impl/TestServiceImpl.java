package com.online.quiz.service.impl;

import com.online.quiz.dto.*;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.*;
import com.online.quiz.model.mapper.Mapper;
import com.online.quiz.repository.*;
import com.online.quiz.service.TestResultService;
import com.online.quiz.service.TestService;
import com.online.quiz.service.UserService;
import com.online.quiz.util.DateTimeUtil;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

  private final TestRepository testRepository;
  private final TestSettingsRepository testSettingsRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  private final Mapper<Test, TestDetailsDTO> testDetailsDTOMapper;
  private final Mapper<Test, TestDTO> testToDTOMapper;
  private final Mapper<Test, TestShortDetails> testShortDetailsMapper;
  private final Mapper<TestResult, TestResultDTO> testResultDTOMapper;

  private final UserService userService;
  private final UserRepository userRepository;
  private final TestResultService testResultService;
  private final AnswerTypeRepository answerTypeRepository;

  private final Map<String, Long> answerTypes = new HashMap<>();

  public TestServiceImpl(TestRepository testRepository, TestSettingsRepository testSettingsRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, Mapper<Test, TestDetailsDTO> testDetailsDTOMapper, Mapper<Test, TestDTO> testToDTOMapper, Mapper<TestResult, TestResultDTO> testResultDTOMapper, Mapper<Test, TestShortDetails> testShortDetailsMapper, UserService userService, UserRepository userRepository, TestResultService testResultService, AnswerTypeRepository answerTypeRepository) {
    this.testRepository = testRepository;
    this.testSettingsRepository = testSettingsRepository;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
    this.testDetailsDTOMapper = testDetailsDTOMapper;
    this.testToDTOMapper = testToDTOMapper;
    this.testResultDTOMapper = testResultDTOMapper;
    this.testShortDetailsMapper = testShortDetailsMapper;
    this.userService = userService;
    this.userRepository = userRepository;
    this.testResultService = testResultService;
    this.answerTypeRepository = answerTypeRepository;

    answerTypeRepository.findAll().forEach(answerType -> answerTypes.put(answerType.getName(), answerType.getId()));
  }

  @Transactional
  @Override
  public void create(TestDTO testDTO) {
    // Test settings mapping
    TestSettings testSettings = getTestSettings(testDTO);

    testSettings = testSettingsRepository.save(testSettings);

    // General info test mapping
    Test test = new Test();
    test.setUser(userService.findUserByEmail(userService.getCurrentUserEmail()));
    test.setDescription(testDTO.getDescription());
    test.setTitle(testDTO.getTitle());
    test.setSettings(testSettings);
    test.setAvailable(true);
    test = testRepository.save(test);

    // Questions mapping
    for (QuestionDTO questionDTO : testDTO.getQuestions()) {
      Question question = new Question();

      AnswerType answerType = answerTypeRepository.findById(answerTypes.get(questionDTO.getAnswerType())).get();

      question.setAnswerType(answerType);
      question.setValue(questionDTO.getValue());
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

  private TestSettings getTestSettings(TestDTO testDTO) {
    TestSettingsDTO testSettingsDTO = testDTO.getSettings();

    TestSettings testSettings = new TestSettings();

    if (testSettingsDTO.getAvailableFrom() != null) {
      testSettings.setAvailableFrom(DateTimeUtil.parseDefaultDateTime(testSettingsDTO.getAvailableFrom()));
    }
    if (testSettingsDTO.getAvailableTo() != null) {
      testSettings.setAvailableTo(DateTimeUtil.parseDefaultDateTime(testSettingsDTO.getAvailableTo()));
    }

    return testSettings;
  }

  @Override
  public PaginationDTO<TestDetailsDTO> getAllTests(Pageable pageable) {
    Page<Test> testsPage = testRepository.findAllBy(pageable);

    return new PaginationDTO<>(testDetailsDTOMapper.mapList(testsPage.getContent()), testsPage);
  }

  @Override
  public PaginationDTO<TestDetailsDTO> getTestsForCurrentUser(Pageable pageable) {
    User currentUser = userService.getCurrentUser();
    Page<Test> testsPage = testRepository.findAllByUserId(pageable, currentUser.getId());

    return new PaginationDTO<>(testDetailsDTOMapper.mapList(testsPage.getContent()), testsPage);
  }

  @Override
  public PaginationDTO<SharedTestDetailsDTO> getSharedTestsForCurrentUser(Pageable pageable) throws NotFoundException {
    List<SharedTestDetailsDTO> sharedTestDetailsDTOs = new ArrayList<>();
    User user = userRepository.findById(userService.getCurrentUser().getId()).orElseThrow(() -> new NotFoundException("User not found"));

    Page<Test> testsPage = new PageImpl<>(new ArrayList<>(user.getTests()), pageable, user.getTests().size());

    testsPage.getContent().forEach(test -> {
      TestResult testResult = testResultService.getTestResultByTestId(test.getId());
      SharedTestDetailsDTO sharedTestDetailsDTO = SharedTestDetailsDTO.builder().testResult(testResultDTOMapper.map(testResult)).testDetails(testDetailsDTOMapper.map(test)).build();
      sharedTestDetailsDTOs.add(sharedTestDetailsDTO);
    });

    return new PaginationDTO<>(sharedTestDetailsDTOs, testsPage);
  }

  @Override
  public TestDTO getTest(Long id) {
    Test test = testRepository.findTestById(id);
    Comparator<Question> compareBySequence = Comparator.comparing(Question::getSequence);

    List<Question> sortedQuestions = test.getQuestions().stream().sorted(compareBySequence).collect(Collectors.toList());
    test.setQuestions(sortedQuestions);

    return testToDTOMapper.map(test);
  }

  @Override
  public void deleteTests(List<Long> testIds) {
    List<Test> tests = testRepository.findAllById(testIds);
    if (!testIds.isEmpty()) {
      tests.forEach(test -> {
        test.setUsers(null);
        testRepository.save(test);
      });
      testIds.forEach(testRepository::deleteById);
    }
  }

  @Override
  public List<TestShortDetails> getAllTestsShortDetails() {
    User currentUser = userService.getCurrentUser();
    return this.testShortDetailsMapper.mapList(testRepository.findAllByUserId(currentUser.getId()));
  }

}
