package com.online.quiz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserGroup extends AbstractEntity {

  @ManyToOne
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
