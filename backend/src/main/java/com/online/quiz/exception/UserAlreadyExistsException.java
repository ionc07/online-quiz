package com.online.quiz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
  private String message;

}
