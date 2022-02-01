package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestDTO;
import com.online.quiz.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestToDtoMapper extends Mapper<Test, TestDTO> {

  private final QuestionToDtoMapper questionToDtoMapper;

  private final TestSettingsToDtoMapper testSettingsToDtoMapper;

  @Override
  public TestDTO map(Test entity) {
    return TestDTO.builder()
            .description(entity.getDescription())
            .questions(questionToDtoMapper.mapList(entity.getQuestions()))
            .settings(testSettingsToDtoMapper.map(entity.getSettings()))
            .title(entity.getTitle())
            .build();
  }
}
