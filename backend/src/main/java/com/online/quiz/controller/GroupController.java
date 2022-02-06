package com.online.quiz.controller;

import com.online.quiz.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
  private final GroupService groupService;

  @GetMapping
  public ResponseEntity<?> getAll() {
    return new ResponseEntity(groupService.getAll(), HttpStatus.OK);
  }
}
