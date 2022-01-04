package com.online.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuantityStatisticsDTO {
  private Long usersCount;
  private Long testsCount;
  private Long testEventCount;
}
