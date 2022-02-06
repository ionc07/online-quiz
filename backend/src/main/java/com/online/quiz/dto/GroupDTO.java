package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupDTO {
  private String title;
  private List<Long> tests;
  private List<Long> users;
}
