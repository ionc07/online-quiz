package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestShortDetails {
  private Long id;
  private String title;
}
