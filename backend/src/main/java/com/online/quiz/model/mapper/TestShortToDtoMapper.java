package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestShortDetailsDTO;
import com.online.quiz.model.Test;
import org.springframework.stereotype.Component;

@Component
public class TestShortToDtoMapper extends Mapper<Test, TestShortDetailsDTO> {
  @Override
  public TestShortDetailsDTO map(Test entity) {
    return TestShortDetailsDTO.builder()
            .available(true)
            .availableFrom(entity.getSettings().getAvailableFrom().format(formatter))
            .availableTo(entity.getSettings().getAvailableTo().format(formatter))
            .creatorName(entity.getUser().getFirstName() + entity.getUser().getLastName())
            .title(entity.getTitle())
            .id(entity.getId())
            .build();
  }
}
