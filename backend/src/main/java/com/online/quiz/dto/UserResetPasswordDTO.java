package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserResetPasswordDTO {

  @NotNull(message = "Email is mandatory")
  @Email(message = "Email must be well-formed")
  private String email;

  @NotNull
  private String resetCode;

  @NotNull(message = "Last name must not be null")
  @Size(min = 2, message = "Last name must not be less than 2 characters")
  private String newPassword;

}
