package com.online.quiz.controller;

import com.online.quiz.dto.SharedTestDetailsDTO;
import com.online.quiz.dto.TestDTO;
import com.online.quiz.dto.TestDetailsDTO;
import com.online.quiz.dto.TestShortDetails;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public ResponseEntity<PaginationDTO<TestDetailsDTO>> getAll(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
    return new ResponseEntity<>(testService.getAllTests(pageable), HttpStatus.OK);
  }

  @GetMapping("/short-details")
  public ResponseEntity<List<TestShortDetails>> getAllTestsShortDetails(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
    return new ResponseEntity<>(testService.getAllTestsShortDetails(), HttpStatus.OK);
  }

  @GetMapping("/currentUser")
  @Operation(summary = "Returns all tests by current user")
  public ResponseEntity<PaginationDTO<TestDetailsDTO>> getTestsForCurrentUser(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
    return new ResponseEntity<>(testService.getTestsForCurrentUser(pageable), HttpStatus.OK);
  }

  @GetMapping("/currentUser/shared")
  public ResponseEntity<PaginationDTO<SharedTestDetailsDTO>> getSharedTestsForCurrentUser(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) throws NotFoundException {
    return new ResponseEntity<>(testService.getSharedTestsForCurrentUser(pageable), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TestDTO> getTest(@PathVariable Long id) {
    return new ResponseEntity<>(testService.getTest(id), HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<?> delete(@RequestParam List<Long> testIds) {
    testService.deleteTests(testIds);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
