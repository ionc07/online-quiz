package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestDetailsDTO;
import com.online.quiz.model.Test;
import org.springframework.stereotype.Component;

@Component
public class TestDetailsToDtoMapper extends Mapper<Test, TestDetailsDTO> {
  @Override
  public TestDetailsDTO map(Test entity) {
    return TestDetailsDTO.builder()
            .available(entity.isAvailable())
            .availableFrom(entity.getSettings().getAvailableFrom().format(formatter))
            .availableTo((entity.getSettings().getAvailableTo() != null ? entity.getSettings().getAvailableTo().format(formatter) : null))
            .creatorName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName())
            .title(entity.getTitle())
            .questionsCount(entity.getQuestions().size())
            .testGroupName(entity.getTestGroup() != null ? entity.getTestGroup().getName() : null)
            .id(entity.getId())
            .build();
  }
}
