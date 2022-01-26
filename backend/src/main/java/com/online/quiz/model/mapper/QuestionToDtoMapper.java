package com.online.quiz.model.mapper;

import com.online.quiz.dto.QuestionDTO;
import com.online.quiz.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionToDtoMapper implements Mapper<Question, QuestionDTO> {

  private final AnswerToDtoMapper answerToDtoMapper;

  @Override
  public QuestionDTO map(Question entity) {
    return QuestionDTO.builder()
            .answers(answerToDtoMapper.mapList(entity.getAnswers()))
            .score(entity.getScore())
            .sequence(entity.getSequence())
            .timeLimit(entity.getTimeLimit())
            .value(entity.getValue())
            .waitForTimeLimit(entity.getWaitForTimeLimit())
            .build();
  }
}