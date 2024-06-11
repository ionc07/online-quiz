package com.online.quiz.model.mapper;

import com.online.quiz.dto.UserDTO;
import com.online.quiz.model.User;
import com.online.quiz.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoMapper extends Mapper<User, UserDTO> {

  @Override
  public UserDTO map(User entity) {
    return UserDTO.builder()
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .email(entity.getEmail())
            .createdAt(DateTimeUtil.formatShortDateTime(entity.getCreatedAt()))
            .role(entity.getRole().getName())
            .build();
  }
}
