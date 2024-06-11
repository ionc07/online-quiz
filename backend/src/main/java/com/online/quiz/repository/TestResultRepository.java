package com.online.quiz.repository;

import com.online.quiz.model.TestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
  TestResult findByUserIdAndTestId(Long userId, Long testId);

  @Query("SELECT tr FROM TestResult tr WHERE tr.test.user.id = :userId AND (:testIds IS NULL OR tr.test.id IN :testIds)")
  Page<TestResult> findByTestUserIdAndTestIds(@Param("userId") Long userId, @Param("testIds") List<Long> testIds, Pageable pageable);

}