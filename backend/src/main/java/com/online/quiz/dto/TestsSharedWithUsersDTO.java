package com.online.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestsSharedWithUsersDTO {
  private List<Long> userIds;
  private List<Long> testIds;
}
