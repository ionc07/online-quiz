package com.online.quiz.model;

import javax.persistence.*;

@Entity
public class UserResponse extends AbstractEntity {

  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  @ManyToOne
  @JoinColumn(name = "test_id", nullable = false)
  private Test test;

  @ManyToOne
  @JoinColumn(name = "question_id", nullable = false)
  private Question question;

  @ManyToOne
  @JoinColumn(name = "answer_id", nullable = false)
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

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
  }
}
