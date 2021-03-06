package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TestSettingsDTO {
  private String availableFrom;

  private String availableTo;

  private Boolean chatEnabled;

  private Integer timeLimit;

  private Integer maxAttempts;
}
