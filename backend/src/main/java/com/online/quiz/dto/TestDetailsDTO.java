package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestDetailsDTO {
  private Long id;
  private String title;
  private String creatorName;
  private String availableFrom;
  private String availableTo;
  private Boolean available;
  private Integer questionsCount;
  private String testGroupName;
}
