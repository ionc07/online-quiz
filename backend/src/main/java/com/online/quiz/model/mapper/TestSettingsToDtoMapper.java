package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestSettingsDTO;
import com.online.quiz.model.TestSettings;
import com.online.quiz.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class TestSettingsToDtoMapper extends Mapper<TestSettings, TestSettingsDTO> {
  @Override
  public TestSettingsDTO map(TestSettings entity) {
    return TestSettingsDTO.builder()
            .availableFrom(DateTimeUtil.formatShortDateTime(entity.getAvailableFrom()))
            .availableTo((entity.getAvailableTo() != null ? DateTimeUtil.formatShortDateTime(entity.getAvailableTo()) : null))
            .build();
  }
}
