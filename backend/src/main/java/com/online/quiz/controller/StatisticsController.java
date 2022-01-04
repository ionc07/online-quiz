package com.online.quiz.controller;

import com.online.quiz.dto.QuantityStatisticsDTO;
import com.online.quiz.projection.TestCreationsStatistics;
import com.online.quiz.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

  private final StatisticsService statisticsService;

  @GetMapping("/quantitative")
  @Operation(summary = "Get the quantitative info about the system")
  public ResponseEntity<QuantityStatisticsDTO> getCurrentUser() {
    return new ResponseEntity<>(statisticsService.getQuantityStatistics(), HttpStatus.OK);
  }

  @GetMapping("/testCreations")
  public ResponseEntity<List<TestCreationsStatistics>> getWeeklyTestCreations() {
    return new ResponseEntity(statisticsService.getWeeklyCreationCount(), HttpStatus.OK);
  }
}
