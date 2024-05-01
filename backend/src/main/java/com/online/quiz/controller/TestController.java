package com.online.quiz.controller;

import com.online.quiz.dto.TestDTO;
import com.online.quiz.dto.TestShortDetailsDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

  private final TestService testService;

  @PostMapping
  @Operation(summary = "Creates the test")
  public ResponseEntity<?> create(@RequestBody TestDTO testDTO) {
    testService.create(testDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping
  @Operation(summary = "Returns all tests")
  public ResponseEntity<PaginationDTO<TestShortDetailsDTO>> getAll(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
    return new ResponseEntity<>(testService.getAllTests(pageable), HttpStatus.OK);
  }

  @GetMapping("/currentUser")
  @Operation(summary = "Returns all tests by current user")
  public ResponseEntity<PaginationDTO<TestShortDetailsDTO>> getAllForCurrentUser(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
    return new ResponseEntity<>(testService.getTestsForCurrentUser(pageable), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TestDTO> getTest(@PathVariable Long id) {
    return new ResponseEntity<>(testService.getTest(id), HttpStatus.OK);
  }
}
