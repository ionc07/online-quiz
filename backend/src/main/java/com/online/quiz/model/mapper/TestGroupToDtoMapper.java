package com.online.quiz.model.mapper;

import com.online.quiz.dto.TestGroupDTO;
import com.online.quiz.model.TestGroup;
import org.springframework.stereotype.Component;

@Component
public class TestGroupToDtoMapper extends Mapper<TestGroup, TestGroupDTO> {
  @Override
  public TestGroupDTO map(TestGroup entity) {
    return TestGroupDTO.builder().id(entity.getId()).name(entity.getName()).testsCount(entity.getTests().size())
            .build();
  }

}
