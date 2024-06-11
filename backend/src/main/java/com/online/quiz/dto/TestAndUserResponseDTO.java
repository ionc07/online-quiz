package com.online.quiz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TestAndUserResponseDTO {
  private TestDTO test;
  private List<UserResponseDTO> userResponses;
}
