package com.online.quiz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadCredentialsException extends RuntimeException {
  private String message;

}
