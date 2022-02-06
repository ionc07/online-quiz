package com.online.quiz.model.mapper;

import com.online.quiz.dto.GroupDTO;
import com.online.quiz.model.Group;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GroupToDtoMapper extends Mapper<Group, GroupDTO> {
  @Override
  public GroupDTO map(Group entity) {
    return GroupDTO.builder().title(entity.getTitle()).tests(entity.getTestGroups().stream().map(test -> test.getTest().getId()).collect(Collectors.toList()))
            .users(entity.getUserGroups().stream().map(user -> user.getUser().getId()).collect(Collectors.toList()))
            .build();
  }

}
