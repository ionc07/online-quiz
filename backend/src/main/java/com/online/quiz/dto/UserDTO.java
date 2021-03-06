package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
  private String firstName;

  private String lastName;

  private String email;

  private String createdAt;

  private String role;

  private String resetCode;
}
