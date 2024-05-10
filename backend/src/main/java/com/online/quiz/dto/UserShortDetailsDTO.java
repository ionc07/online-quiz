package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserShortDetailsDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
}
