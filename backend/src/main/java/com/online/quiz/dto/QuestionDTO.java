package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class QuestionDTO {

  private String value;

  private Integer sequence;

  private Integer timeLimit;

  private Boolean waitForTimeLimit;

  private Integer score;

  private List<AnswerDTO> answers;

}
