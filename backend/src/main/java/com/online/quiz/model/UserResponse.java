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

  private String value;

  private Integer score;

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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
