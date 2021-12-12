package com.online.quiz.controller;

import com.online.quiz.model.Test;
import com.online.quiz.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

  private final TestService testService;

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Test test) {
    testService.create(test);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(testService.getAllTests(), HttpStatus.OK);
  }
}
