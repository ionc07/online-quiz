package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class AuthRequest {

  @NotEmpty
  @Email(message = "Email is not well-formed")
  private String email;

  @NotEmpty
  @Size(min = 8, max = 32)
  private String password;
}