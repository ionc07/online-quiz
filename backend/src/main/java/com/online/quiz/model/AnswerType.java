package com.online.quiz.model;

import javax.persistence.Entity;

@Entity
public class AnswerType extends AbstractEntity {

  private String name;

  public AnswerType() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
