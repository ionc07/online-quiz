package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestSettingsDTO;
import com.online.quiz.model.TestSettings;
import org.springframework.stereotype.Component;

@Component
public class TestSettingsToDtoMapper implements Mapper<TestSettings, TestSettingsDTO> {
  @Override
  public TestSettingsDTO map(TestSettings entity) {
    return TestSettingsDTO.builder()
            .availableFrom(entity.getAvailableFrom())
            .availableTo(entity.getAvailableTo())
            .chatEnabled(entity.isChatEnabled())
            .maxAttempts(entity.getMaxAttempts())
            .timeLimit(entity.getTimeLimit())
            .build();
  }
}
