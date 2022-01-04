package com.online.quiz.projection;

import org.springframework.beans.factory.annotation.Value;

public interface TestShortDetails {
  Long getId();

  String getTitle();

  @Value("#{target.user.firstName + ' ' + target.user.lastName}")
  String getCreatorName();
}
