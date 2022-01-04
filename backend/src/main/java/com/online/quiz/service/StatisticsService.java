package com.online.quiz.service;

import com.online.quiz.dto.QuantityStatisticsDTO;
import com.online.quiz.projection.TestCreationsStatistics;

import java.util.List;

public interface StatisticsService {
  QuantityStatisticsDTO getQuantityStatistics();

  List<TestCreationsStatistics> getWeeklyCreationCount();
}
