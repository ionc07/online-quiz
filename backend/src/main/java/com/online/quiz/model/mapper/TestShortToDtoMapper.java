package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestShortDetails;
import com.online.quiz.model.Test;
import org.springframework.stereotype.Component;

@Component
public class TestShortToDtoMapper extends Mapper<Test, TestShortDetails> {
  @Override
  public TestShortDetails map(Test entity) {
    return TestShortDetails.builder()
            .title(entity.getTitle())
            .id(entity.getId())
            .build();
  }
}
