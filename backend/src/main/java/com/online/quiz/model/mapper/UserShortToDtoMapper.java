package com.online.quiz.model.mapper;

import com.online.quiz.dto.UserShortDetailsDTO;
import com.online.quiz.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserShortToDtoMapper extends Mapper<User, UserShortDetailsDTO> {
  @Override
  public UserShortDetailsDTO map(User entity) {
    return UserShortDetailsDTO.builder()
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .email(entity.getEmail())
            .id(entity.getId())
            .build();
  }
}
