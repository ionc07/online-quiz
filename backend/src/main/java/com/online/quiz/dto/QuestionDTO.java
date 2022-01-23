package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class QuestionDTO {

  private String value;

  private Integer sequence;

  private Integer timeLimit;

  private Boolean waitForTimeLimit;

  private Integer score;

  private List<AnswerDTO> answers;

}
