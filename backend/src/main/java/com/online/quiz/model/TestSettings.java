package com.online.quiz.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class TestSettings extends AbstractEntity {

  private LocalDateTime availableFrom;

  private LocalDateTime availableTo;

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

}
