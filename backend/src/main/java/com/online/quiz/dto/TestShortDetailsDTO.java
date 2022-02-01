package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TestShortDetailsDTO {
  private Long id;
  private String title;
  private String creatorName;
  private String availableFrom;
  private String availableTo;
  private Boolean available;
}
