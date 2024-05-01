package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestShortDetailsDTO;
import com.online.quiz.model.Test;
import org.springframework.stereotype.Component;

@Component
public class TestShortToDtoMapper extends Mapper<Test, TestShortDetailsDTO> {
  @Override
  public TestShortDetailsDTO map(Test entity) {
    return TestShortDetailsDTO.builder()
            .available(entity.isAvailable())
            .availableFrom(entity.getSettings().getAvailableFrom().format(formatter))
            .availableTo((entity.getSettings().getAvailableTo() != null ? entity.getSettings().getAvailableTo().format(formatter) : null))
            .creatorName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName())
            .title(entity.getTitle())
            .questionsCount(entity.getQuestions().size())
            .id(entity.getId())
            .build();
  }
}
