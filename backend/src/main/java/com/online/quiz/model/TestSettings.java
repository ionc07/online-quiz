package com.online.quiz.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class TestSettings extends AbstractEntity {

  private LocalDateTime availableFrom;

  private LocalDateTime availableTo;

  private Boolean chatEnabled;

  private Integer timeLimit;

  private Integer maxAttempts;

  public TestSettings() {
  }

  public LocalDateTime getAvailableFrom() {
    return availableFrom;
  }

  public void setAvailableFrom(LocalDateTime availableFrom) {
    this.availableFrom = availableFrom;
  }

  public LocalDateTime getAvailableTo() {
    return availableTo;
  }

  public void setAvailableTo(LocalDateTime availableTo) {
    this.availableTo = availableTo;
  }

  public Boolean isChatEnabled() {
    return chatEnabled;
  }

  public void setChatEnabled(Boolean chatEnabled) {
    this.chatEnabled = chatEnabled;
  }

  public Integer getTimeLimit() {
    return timeLimit;
  }

  public void setTimeLimit(Integer timeLimit) {
    this.timeLimit = timeLimit;
  }

  public Integer getMaxAttempts() {
    return maxAttempts;
  }

  public void setMaxAttempts(Integer maxAttempts) {
    this.maxAttempts = maxAttempts;
  }

  public Boolean getChatEnabled() {
    return chatEnabled;
  }
}
