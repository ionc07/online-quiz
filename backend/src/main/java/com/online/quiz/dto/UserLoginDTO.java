package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class UserLoginDTO {

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  private String password;

}
