package com.online.quiz.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "test_id")
  private Test test;

  @ManyToOne
  @JoinColumn(name = "answer_type_id")
  private AnswerType answerType;

  private String value;

  private Integer sequence;

  private Integer timeLimit;

  private Boolean waitForTimeLimit;

  private Integer score;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private List<Answer> answers;

  public Question() {
  }

  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
  }

  public AnswerType getAnswerType() {
    return answerType;
  }

  public void setAnswerType(AnswerType answerType) {
    this.answerType = answerType;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Integer getSequence() {
    return sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public Integer getTimeLimit() {
    return timeLimit;
  }

  public void setTimeLimit(Integer timeLimit) {
    this.timeLimit = timeLimit;
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

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }
}
