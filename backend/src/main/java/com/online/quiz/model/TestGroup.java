package com.online.quiz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TestGroup extends AbstractEntity {
  private String name;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany
  @JoinTable(name = "test_group_test",
          joinColumns = @JoinColumn(name = "test_group_id"),
          inverseJoinColumns = @JoinColumn(name = "test_id"))
  private List<Test> tests = new ArrayList<>();

  public TestGroup() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Test> getTests() {
    return tests;
  }

  public void setTests(List<Test> tests) {
    this.tests = tests;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
