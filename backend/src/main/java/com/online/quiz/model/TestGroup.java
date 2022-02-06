package com.online.quiz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TestGroup extends AbstractEntity {

  @ManyToOne
  private Test test;

  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
  }
}
