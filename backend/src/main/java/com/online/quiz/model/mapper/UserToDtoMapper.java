package com.online.quiz.model.mapper;

import com.online.quiz.dto.UserDto;
import com.online.quiz.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoMapper implements Mapper<User, UserDto>{

  @Override
  public UserDto map(User entity) {
    return UserDto.builder()
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .email(entity.getEmail())
            .createdAt(entity.getCreatedAt().toString())
            .role(entity.getRole().getName())
            .build();
  }
}
