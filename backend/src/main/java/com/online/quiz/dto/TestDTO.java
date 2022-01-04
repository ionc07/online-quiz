package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TestDTO {

  private String title;

  private String description;

  private TestSettingsDTO settings;

  private List<QuestionDTO> questions;

}
