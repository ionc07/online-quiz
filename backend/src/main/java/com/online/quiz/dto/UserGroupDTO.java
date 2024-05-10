package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserGroupDTO {
  private Long id;
  private String name;
  private List<UserShortDetailsDTO> users;
}
