package com.online.quiz.model.mapper;

import com.online.quiz.dto.AnswerDTO;
import com.online.quiz.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerToDtoMapper extends Mapper<Answer, AnswerDTO> {
  @Override
  public AnswerDTO map(Answer entity) {
    return AnswerDTO.builder()
            .answerType(entity.getAnswerType().getName())
            .correct(entity.getCorrect())
            .score(entity.getScore())
            .value(entity.getValue())
            .build();
  }
}
