package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestResultDTO {
  private Long id;
  private String testName;
  private Long testId;
  private UserShortDetailsDTO userShortDetails;
  private Integer score;
  private Integer totalScore;
  private Integer correctQuestions;
  private Integer totalQuestions;
  private String takenAt;
}
