package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AnswerDTO {
  private AnswerTypeDTO answerType;

  private String value;

  private Integer score;

  private Boolean correct;
}
