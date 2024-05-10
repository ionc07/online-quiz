package com.online.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestsSharedWIthUsersDTO {
  private List<Long> userIds;
  private List<Long> testIds;
}
