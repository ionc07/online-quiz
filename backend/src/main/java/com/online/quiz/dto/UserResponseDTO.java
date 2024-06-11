package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponseDTO {
  private Long questionId;
  private List<Long> selectedAnswerIds;
}
