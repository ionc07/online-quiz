package com.online.quiz.aspect;

import com.online.quiz.exception.UserNotFoundException;
import com.online.quiz.exception.WrongResetCodeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice
        extends ResponseEntityExceptionHandler {

  @ExceptionHandler({UserNotFoundException.class, WrongResetCodeException.class})
  protected ResponseEntity<Object> handleConflict(
          RuntimeException ex, WebRequest request) {

    return ResponseEntity.badRequest().body(ex);
  }

}