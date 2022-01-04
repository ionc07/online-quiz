package com.online.quiz.controller;

import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.dto.UserUpdateDTO;
import com.online.quiz.model.User;
import com.online.quiz.projection.UserDetails;
import com.online.quiz.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

  @GetMapping
  @Operation(summary = "Get all users")
  public ResponseEntity<Page<User>> getAll(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    return new ResponseEntity(userService.getAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/current")
  @Operation(summary = "Get the user that is currently logged in the system")
  public ResponseEntity<UserDetails> getCurrentUser() {
    UserDetails userDetails = userService.getCurrentUser();

    return new ResponseEntity<>(userDetails, HttpStatus.OK);
  }

  @PostMapping("/password/verification")
  @Operation(summary = "Send a reset code to user email")
  public ResponseEntity<?> verifyUser(@RequestParam String email) {
    userService.verify(email);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/password/reset")
  @Operation(summary = "Changes the user password")
  public ResponseEntity<?> resetPassword(@RequestBody UserResetPasswordDTO userResetPasswordDTO) {
    userService.resetPassword(userResetPasswordDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  @Operation(summary = "Updates user details")
  public ResponseEntity<?> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
    userService.update(userUpdateDTO);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletes a user by id")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    userService.delete(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
