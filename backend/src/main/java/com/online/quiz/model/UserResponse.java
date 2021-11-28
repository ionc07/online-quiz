package com.online.quiz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class UserResponse extends AbstractEntity {

  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  @OneToOne(cascade = CascadeType.ALL)
  private Answer answer;

  public UserResponse() {
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Answer getAnswer() {
    return answer;
  }

  public void setAnswer(Answer answer) {
    this.answer = answer;
  }
}
