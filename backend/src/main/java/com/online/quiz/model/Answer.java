package com.online.quiz.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer extends AbstractEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "answer_type_id")
  private AnswerType answerType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;

  private String value;

  private Boolean correct;

  public Answer() {
  }

  public AnswerType getAnswerType() {
    return answerType;
  }

  public void setAnswerType(AnswerType answerType) {
    this.answerType = answerType;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Boolean getCorrect() {
    return correct;
  }

  public void setCorrect(Boolean correct) {
    this.correct = correct;
  }
}
