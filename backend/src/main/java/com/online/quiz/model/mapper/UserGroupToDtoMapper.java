package com.online.quiz.model.mapper;

import com.online.quiz.dto.UserGroupDTO;
import com.online.quiz.model.UserGroup;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserGroupToDtoMapper extends Mapper<UserGroup, UserGroupDTO> {
  private final UserShortToDtoMapper userShortToDtoMapper;

  @Override
  public UserGroupDTO map(UserGroup entity) {
    return UserGroupDTO.builder().id(entity.getId()).name(entity.getName()).users(userShortToDtoMapper.mapList(entity.getUsers()))
            .build();
  }

}
