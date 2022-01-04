package com.online.quiz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.List;

@Entity
public class Test extends AbstractEntity {

  private String title;

  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne(cascade = CascadeType.ALL)
  private TestSettings settings;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "test_id")
  private List<Question> questions;

  private Date createdAt;

  public Test() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public TestSettings getSettings() {
    return settings;
  }

  public void setSettings(TestSettings settings) {
    this.settings = settings;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  @PrePersist
  public void setCreatedAt() {
    this.createdAt = new Date();
  }
}
