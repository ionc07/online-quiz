package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDTO {

  @Size(min = 2, message = "First name must not be less than 2 characters")
  private String firstName;

  @Size(min = 2, message = "Last name must not be less than 2 characters")
  private String lastName;

  @Email(message = "Email must be well-formed")
  private String email;

  @Size(min = 8, message = "Password must be greater than 7 characters!")
  private String password;

}
