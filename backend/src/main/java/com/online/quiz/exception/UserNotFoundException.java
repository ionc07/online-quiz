package com.online.quiz.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
  private String message;

  public UserNotFoundException(String message) {
    super(message);
  }
}
