package com.online.quiz.service.impl;

import com.online.quiz.dto.TestResultDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.Test;
import com.online.quiz.model.TestResult;
import com.online.quiz.model.User;
import com.online.quiz.model.mapper.Mapper;
import com.online.quiz.repository.TestResultRepository;
import com.online.quiz.service.TestResultService;
import com.online.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

  private final TestResultRepository testResultRepository;
  private final UserService userService;

  private final Mapper<TestResult, TestResultDTO> testResultMapper;

  @Override
  public List<TestResult> getAllTestResults() {
    return testResultRepository.findAll();
  }

  @Override
  public TestResult getTestResultByTestId(Long testId) {
    User currentUser = userService.getCurrentUser();
    return testResultRepository.findByUserIdAndTestId(currentUser.getId(), testId);
  }

  @Override
  public PaginationDTO<TestResultDTO> getTestResultsForUserTests(List<Long> testIds, Pageable pageable) {
    User currentUser = userService.getCurrentUser();
    Page<TestResult> testResultsPage = testResultRepository.findByTestUserIdAndTestIds(currentUser.getId(), testIds, pageable);

    return new PaginationDTO<>(testResultMapper.mapList(testResultsPage.getContent()), testResultsPage);
  }

  @Override
  public void saveTestResult(Long testId, TestResult testResult) {
    User currentUser = userService.getCurrentUser();

    testResult.setTest(new Test(testId));
    testResult.setUser(currentUser);

    testResultRepository.save(testResult);
  }

  @Override
  public void deleteTestResults(List<Long> testResultIds) {
    testResultRepository.deleteAllById(testResultIds);
  }

}