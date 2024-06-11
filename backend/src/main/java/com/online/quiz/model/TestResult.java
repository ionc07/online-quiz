package com.online.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class TestResult extends AbstractEntity {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "test_id", nullable = false)
  private Test test;

  private Integer score;

  private Integer totalScore;

  @Column(nullable = false)
  private Integer correctQuestions;

  @Column(nullable = false)
  private LocalDateTime takenAt = LocalDateTime.now();

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(Integer totalScore) {
    this.totalScore = totalScore;
  }

  public Integer getCorrectQuestions() {
    return correctQuestions;
  }

  public void setCorrectQuestions(Integer correctAnswers) {
    this.correctQuestions = correctAnswers;
  }

  public LocalDateTime getTakenAt() {
    return takenAt;
  }

  public void setTakenAt(LocalDateTime takenAt) {
    this.takenAt = takenAt;
  }
}