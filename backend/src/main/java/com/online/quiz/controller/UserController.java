package com.online.quiz.controller;

import com.online.quiz.dto.TestsSharedWithUsersDTO;
import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.dto.UserShortDetailsDTO;
import com.online.quiz.dto.UserUpdateDTO;
import com.online.quiz.model.User;
import com.online.quiz.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  @Operation(summary = "Get all users")
  public ResponseEntity<UserShortDetailsDTO> getAll(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    return new ResponseEntity(userService.getAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/current")
  @Operation(summary = "Get the user that is currently logged in the system")
  public ResponseEntity<User> getCurrentUser() {
    User user = userService.getCurrentUser();

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/details")
  public ResponseEntity<UserShortDetailsDTO> getUserShortDetailsByEmail(@RequestParam String email) {
    return ResponseEntity.ok(userService.getUserByEmail(email));
  }

  @GetMapping("/by-group")
  public ResponseEntity<List<UserShortDetailsDTO>> getAllUsersByUserGroup(@RequestParam Long userGroupId) {
    return ResponseEntity.ok(userService.getAllUsersByUserGroup(userGroupId));
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

  @PutMapping("/share/tests")
  public ResponseEntity<?> shareTestsWithUsers(@RequestBody TestsSharedWithUsersDTO testsSharedWithUsersDTO) {
    userService.shareTestsWithUsers(testsSharedWithUsersDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletes a user by id")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    userService.delete(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
