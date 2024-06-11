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

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }
}
