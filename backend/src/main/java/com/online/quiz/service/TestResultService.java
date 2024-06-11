package com.online.quiz.service;

import com.online.quiz.dto.TestResultDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.TestResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestResultService {
  List<TestResult> getAllTestResults();

  PaginationDTO<TestResultDTO> getTestResultsForUserTests(List<Long> testIds, Pageable pageable);

  TestResult getTestResultByTestId(Long testId);

  void saveTestResult(Long testId, TestResult testResult);

  void deleteTestResults(List<Long> testResultIds);
}
