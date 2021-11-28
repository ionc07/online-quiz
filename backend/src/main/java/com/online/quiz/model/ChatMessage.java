package com.online.quiz.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ChatMessage extends AbstractEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "answer_type_id")
  private UserTest userTest;

  private String message;

  private Boolean teacher;

  private Date date;

  public ChatMessage() {
  }

  public UserTest getUserTest() {
    return userTest;
  }

  public void setUserTest(UserTest userTest) {
    this.userTest = userTest;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getTeacher() {
    return teacher;
  }

  public void setTeacher(Boolean teacher) {
    this.teacher = teacher;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
