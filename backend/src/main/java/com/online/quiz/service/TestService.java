package com.online.quiz.service;

import com.online.quiz.dto.TestDTO;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.model.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {
  void create(TestDTO testDTO);

  PaginationDTO<TestDTO> getAllTests(Pageable pageable);
}
