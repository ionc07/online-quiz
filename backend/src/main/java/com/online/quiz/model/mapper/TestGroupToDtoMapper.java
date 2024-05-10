package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestGroupDTO;
import com.online.quiz.model.TestGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestGroupToDtoMapper extends Mapper<TestGroup, TestGroupDTO> {

  private final TestShortToDtoMapper testShortToDtoMapper;

  @Override
  public TestGroupDTO map(TestGroup entity) {
    return TestGroupDTO.builder()
            .id(entity.getId()).name(entity.getName()).tests(testShortToDtoMapper.mapList(entity.getTests()))
            .build();
  }

}
