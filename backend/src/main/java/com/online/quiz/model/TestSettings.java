package com.online.quiz.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class TestSettings extends AbstractEntity {

  private Date availableFrom;

  private Date availableTo;

  private Boolean chatEnabled;

  private Integer timeLimit;

  private Integer maxAttempts;

  public TestSettings() {
  }

  public Date getAvailableFrom() {
    return availableFrom;
  }

  public void setAvailableFrom(Date availableFrom) {
    this.availableFrom = availableFrom;
  }

  public Date getAvailableTo() {
    return availableTo;
  }

  public void setAvailableTo(Date availableTo) {
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
