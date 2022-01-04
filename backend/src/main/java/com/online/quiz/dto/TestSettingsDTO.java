package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class TestSettingsDTO {
  private Date availableFrom;

  private Date availableTo;

  private Boolean chatEnabled;

  private Integer timeLimit;

  private Integer maxAttempts;
}
