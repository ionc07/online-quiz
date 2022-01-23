package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Builder
public class TestSettingsDTO {
  private Date availableFrom;

  private Date availableTo;

  private Boolean chatEnabled;

  private Integer timeLimit;

  private Integer maxAttempts;
}
