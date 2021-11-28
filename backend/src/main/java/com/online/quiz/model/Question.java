package com.online.quiz.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question extends AbstractEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "test_id")
  private Test test;

  private Integer sequence;

  private Integer time_limit;

  private Boolean waitForTimeLimit;

  private Integer score;

  public Question() {
  }

  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
  }

  public Integer getSequence() {
    return sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public Integer getTime_limit() {
    return time_limit;
  }

  public void setTime_limit(Integer time_limit) {
    this.time_limit = time_limit;
  }

  public Boolean getWaitForTimeLimit() {
    return waitForTimeLimit;
  }

  public void setWaitForTimeLimit(Boolean waitForTimeLimit) {
    this.waitForTimeLimit = waitForTimeLimit;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
