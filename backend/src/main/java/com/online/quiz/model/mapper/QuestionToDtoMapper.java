package com.online.quiz.model.mapper;

import com.online.quiz.dto.QuestionDTO;
import com.online.quiz.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionToDtoMapper extends Mapper<Question, QuestionDTO> {

  private final AnswerToDtoMapper answerToDtoMapper;

  @Override
  public QuestionDTO map(Question entity) {
    return QuestionDTO.builder()
            .id(entity.getId())
            .answers(answerToDtoMapper.mapList(entity.getAnswers()))
            .answerType(entity.getAnswerType().getName())
            .value(entity.getValue())
            .build();
  }
}
