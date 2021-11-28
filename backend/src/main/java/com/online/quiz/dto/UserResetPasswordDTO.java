package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
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
