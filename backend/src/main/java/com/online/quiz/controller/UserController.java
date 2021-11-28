package com.online.quiz.controller;

import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.model.User;
import com.online.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
    userService.save(user);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/password/verification")
  public ResponseEntity<?> verifyUser(@RequestParam String email) {
    userService.verify(email);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/password/reset")
  public ResponseEntity<?> resetPassword(@RequestBody UserResetPasswordDTO userResetPasswordDTO) {
    userService.updatePassword(userResetPasswordDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
