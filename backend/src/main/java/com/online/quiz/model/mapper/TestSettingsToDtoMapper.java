package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestSettingsDTO;
import com.online.quiz.model.TestSettings;
import org.springframework.stereotype.Component;

@Component
public class TestSettingsToDtoMapper extends Mapper<TestSettings, TestSettingsDTO> {
  @Override
  public TestSettingsDTO map(TestSettings entity) {
    return TestSettingsDTO.builder()
            .availableFrom(entity.getAvailableFrom().format(formatter))
            .availableTo((entity.getAvailableTo() != null ? entity.getAvailableTo().format(formatter) : null))
            .chatEnabled(entity.isChatEnabled())
            .maxAttempts(entity.getMaxAttempts())
            .timeLimit(entity.getTimeLimit())
            .build();
  }
}
