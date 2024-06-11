package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestSettingsDTO {
  private String availableFrom;

  private String availableTo;
}
