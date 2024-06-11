package com.online.quiz.controller;


import com.online.quiz.dto.UserGroupDTO;
import com.online.quiz.model.UserGroup;
import com.online.quiz.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userGroups")
@RequiredArgsConstructor
public class UserGroupController {
  private final UserGroupService userGroupService;

  @GetMapping
  public ResponseEntity<List<UserGroupDTO>> getAllUserGroups() {
    return ResponseEntity.ok(userGroupService.getAllUserGroups());
  }

  @PostMapping
  public ResponseEntity<UserGroup> createUserGroup(@RequestBody UserGroup userGroup) {
    return ResponseEntity.ok(userGroupService.createUserGroup(userGroup));
  }

  @PutMapping
  public ResponseEntity<?> updateUserGroup(@RequestBody UserGroup userGroup) {
    userGroupService.updateUserGroup(userGroup);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/users")
  public ResponseEntity<?> moveUsersToGroup(@RequestParam Long userGroupId, @RequestBody List<Long> testIds) {
    userGroupService.moveUsersToGroup(userGroupId, testIds);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteUserGroup(@RequestParam Long userGroupId) {
    userGroupService.deleteUserGroup(userGroupId);
    return ResponseEntity.noContent().build();
  }
}
