package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  @Email
  private String email;

  @NotNull
  private String password;

}
