package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDTO {

  private String value;

  private Integer score;

  private Boolean correct;
}
