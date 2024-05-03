package com.online.quiz.repository;

import com.online.quiz.model.TestGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestGroupRepository extends JpaRepository<TestGroup, Long> {
  List<TestGroup> findAllByUserId(Long userId);
}
