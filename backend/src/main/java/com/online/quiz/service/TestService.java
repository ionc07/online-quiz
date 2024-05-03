package com.online.quiz.service;

import com.online.quiz.dto.TestDTO;
import com.online.quiz.dto.TestShortDetailsDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {
  void create(TestDTO testDTO);

  PaginationDTO<TestShortDetailsDTO> getAllTests(Pageable pageable);

  PaginationDTO<TestShortDetailsDTO> getTestsForCurrentUser(Pageable pageable);

  TestDTO getTest(Long id);

  void deleteTests(List<Long> testIds);
}
