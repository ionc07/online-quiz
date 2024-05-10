package com.online.quiz.model;

import javax.persistence.*;
import java.time.LocalDateTime;
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

  @ManyToOne
  @JoinTable(name = "test_group_test",
          joinColumns = @JoinColumn(name = "test_id"),
          inverseJoinColumns = @JoinColumn(name = "test_group_id"))
  private TestGroup testGroup;

  private Boolean available;

  private LocalDateTime createdAt;

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

  public TestGroup getTestGroup() {
    return testGroup;
  }

  public void setTestGroup(TestGroup testGroup) {
    this.testGroup = testGroup;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @PrePersist
  public void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  public Boolean isAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }


}
