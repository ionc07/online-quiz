package com.online.quiz.controller;


import com.online.quiz.dto.TestGroupDTO;
import com.online.quiz.model.TestGroup;
import com.online.quiz.service.TestGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testGroups")
@RequiredArgsConstructor
public class TestGroupController {
  private final TestGroupService testGroupService;

  @GetMapping
  public ResponseEntity<List<TestGroupDTO>> getAllTestGroups() {
    return ResponseEntity.ok(testGroupService.getAllTestGroups());
  }

  @PostMapping
  public ResponseEntity<TestGroup> createTestGroup(@RequestBody TestGroup testGroup) {
    return ResponseEntity.ok(testGroupService.createTestGroup(testGroup));
  }

  @PutMapping("/tests")
  public ResponseEntity<?> moveTestsToGroup(@RequestParam Long testGroupId, @RequestBody List<Long> testIds) {
    testGroupService.moveTestsToGroup(testGroupId, testIds);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<?> updateTestGroup(@RequestBody TestGroup testGroup) {
    testGroupService.updateTestGroup(testGroup);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteTestGroup(@RequestParam Long testGroupId) {
    testGroupService.deleteTestGroup(testGroupId);
    return ResponseEntity.noContent().build();
  }
}
