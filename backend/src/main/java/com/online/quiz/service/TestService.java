package com.online.quiz.service;

import com.online.quiz.dto.TestDTO;
import com.online.quiz.dto.TestDetailsDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {
  void create(TestDTO testDTO);

  PaginationDTO<TestDetailsDTO> getAllTests(Pageable pageable);

  PaginationDTO<TestDetailsDTO> getTestsForCurrentUser(Pageable pageable);

  PaginationDTO<TestDetailsDTO> getSharedTestsForCurrentUser(Pageable pageable) throws NotFoundException;

  TestDTO getTest(Long id);

  void deleteTests(List<Long> testIds);
}
