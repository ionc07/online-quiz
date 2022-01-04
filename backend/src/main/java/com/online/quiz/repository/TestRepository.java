package com.online.quiz.repository;

import com.online.quiz.model.Test;
import com.online.quiz.projection.TestCreationsStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
  List<Test> findAllBy();

  @Query(value = "select to_char(created_at, 'yyyy-mm-dd') date, count(id) from test group by date order by date limit 7", nativeQuery = true)
  List<TestCreationsStatistics> findWeeklyCreationsCount();
}
