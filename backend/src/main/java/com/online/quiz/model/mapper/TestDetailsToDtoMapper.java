package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestDetailsDTO;
import com.online.quiz.model.Test;
import com.online.quiz.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class TestDetailsToDtoMapper extends Mapper<Test, TestDetailsDTO> {
  @Override
  public TestDetailsDTO map(Test entity) {
    return TestDetailsDTO.builder()
            .available(entity.isAvailable())
            .availableFrom(DateTimeUtil.formatShortDateTime(entity.getSettings().getAvailableFrom()))
            .availableTo((entity.getSettings().getAvailableTo() != null ? DateTimeUtil.formatShortDateTime(entity.getSettings().getAvailableTo()) : null))
            .creatorName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName())
            .title(entity.getTitle())
            .questionsCount(entity.getQuestions().size())
            .testGroupName(entity.getTestGroup() != null ? entity.getTestGroup().getName() : null)
            .id(entity.getId())
            .build();
  }
}
