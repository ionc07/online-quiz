package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TestGroupDTO {
  private Long id;
  private String name;
  private List<TestShortDetails> tests;
}
