package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestResultDTO;
import com.online.quiz.model.TestResult;
import com.online.quiz.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestResultToDtoMapper extends Mapper<TestResult, TestResultDTO> {
  private final UserShortToDtoMapper userShortToDtoMapper;

  @Override
  public TestResultDTO map(TestResult entity) {
    if (entity == null) {
      return null;
    }
    return TestResultDTO.builder().id(entity.getId())
            .testName(entity.getTest().getTitle())
            .testId(entity.getTest().getId())
            .score(entity.getScore())
            .totalScore(entity.getTotalScore())
            .correctQuestions(entity.getCorrectQuestions())
            .totalQuestions(entity.getTest().getQuestions().size())
            .userShortDetails(userShortToDtoMapper.map(entity.getUser()))
            .takenAt(DateTimeUtil.formatMediumDateTime(entity.getTakenAt()))
            .build();
  }
}
