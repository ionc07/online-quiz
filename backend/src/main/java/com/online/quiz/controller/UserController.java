package com.online.quiz.controller;

import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.dto.UserUpdateDTO;
import com.online.quiz.projection.UserDetails;
import com.online.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/current")
  @ApiOperation("Get the user that is currently logged in the system")
  public ResponseEntity<UserDetails> getCurrentUser() {
    UserDetails userDetails = userService.getCurrentUser();

    return new ResponseEntity<>(userDetails, HttpStatus.OK);
  }

  @PostMapping("/password/verification")
  @ApiOperation("Send a reset code to user email")
  public ResponseEntity<?> verifyUser(@RequestParam String email) {
    userService.verify(email);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/password/reset")
  @ApiOperation("Changes the user password")
  public ResponseEntity<?> resetPassword(@RequestBody UserResetPasswordDTO userResetPasswordDTO) {
    userService.resetPassword(userResetPasswordDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  @ApiOperation("Updates user details")
  public ResponseEntity<?> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
    userService.update(userUpdateDTO);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ApiOperation("Deletes a user by id")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    userService.delete(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
