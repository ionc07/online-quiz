package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TestDTO {

  private String title;

  private String description;

  private TestSettingsDTO settings;

  private List<QuestionDTO> questions;

}
