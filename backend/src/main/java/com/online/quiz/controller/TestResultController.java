package com.online.quiz.controller;

import com.online.quiz.dto.TestResultDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.TestResult;
import com.online.quiz.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-results")
@RequiredArgsConstructor
public class TestResultController {

  private final TestResultService testResultService;

  @GetMapping
  public List<TestResult> getAllTestResults() {
    return testResultService.getAllTestResults();
  }

  @GetMapping("/{testId}")
  public ResponseEntity<TestResult> getTestResultById(@PathVariable Long testId) {
    TestResult testResult = testResultService.getTestResultByTestId(testId);
    if (testResult != null) {
      return ResponseEntity.ok(testResult);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/user-tests")
  public PaginationDTO<TestResultDTO> getTestResultsForCurrentUserTests(@RequestParam(required = false) List<Long> testIds,
                                                                        @PageableDefault(size = 20, sort = "takenAt", direction = Sort.Direction.DESC) Pageable pageable) {
    return testResultService.getTestResultsForUserTests(testIds, pageable);
  }


  @PostMapping
  public ResponseEntity<?> createTestResult(@RequestBody TestResult testResult, @RequestParam Long testId) {
    testResultService.saveTestResult(testId, testResult);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteByTestAndUserIds(@RequestParam List<Long> testResultIds) {
    testResultService.deleteTestResults(testResultIds);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
