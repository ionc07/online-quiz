package com.online.quiz.controller;

import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.dto.UserUpdateDTO;
import com.online.quiz.model.User;
import com.online.quiz.projection.UserDetails;
import com.online.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping("/current")
  public ResponseEntity<?> getCurrentUser() {
    UserDetails userDetails = userService.getCurrentUser();

    return new ResponseEntity<>(userDetails, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> save(@Valid @RequestBody User user) {
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
    userService.resetPassword(userResetPasswordDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
    userService.update(userUpdateDTO);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    userService.delete(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
