package com.online.quiz.model;

import javax.persistence.Entity;

@Entity
public class Role extends AbstractEntity{

  private String name;

  public Role() {
  }

  public String getName() {
    return name;
  }

  public void setName(String role) {
    this.name = role;
  }
}
