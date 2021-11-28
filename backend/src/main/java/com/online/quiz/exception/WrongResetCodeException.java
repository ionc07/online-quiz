package com.online.quiz.exception;

import lombok.Getter;

@Getter
public class WrongResetCodeException extends RuntimeException {
  private String message;

  public WrongResetCodeException(String message) {
    super(message);
  }
}
