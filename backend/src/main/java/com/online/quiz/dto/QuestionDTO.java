package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDTO {
  private Long id;

  private String answerType;

  private String value;

  private Integer sequence;

  private List<AnswerDTO> answers;

}
