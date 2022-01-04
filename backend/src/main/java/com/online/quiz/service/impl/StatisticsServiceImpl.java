package com.online.quiz.service.impl;

import com.online.quiz.dto.QuantityStatisticsDTO;
import com.online.quiz.projection.TestCreationsStatistics;
import com.online.quiz.repository.TestRepository;
import com.online.quiz.repository.UserRepository;
import com.online.quiz.repository.UserTestRepository;
import com.online.quiz.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

  private final UserRepository userRepository;
  private final TestRepository testRepository;
  private final UserTestRepository userTestRepository;

  @Override
  public QuantityStatisticsDTO getQuantityStatistics() {
    Long usersCount = userRepository.count();
    Long testsCount = testRepository.count();
    Long testEvents = userTestRepository.countBySubmitDateIsNull();

    QuantityStatisticsDTO quantityStatisticsDTO = new QuantityStatisticsDTO();
    quantityStatisticsDTO.setUsersCount(usersCount);
    quantityStatisticsDTO.setTestsCount(testsCount);
    quantityStatisticsDTO.setTestEventCount(testEvents);

    return quantityStatisticsDTO;
  }

  @Override
  public List<TestCreationsStatistics> getWeeklyCreationCount() {
    return testRepository.findWeeklyCreationsCount();
  }
}
