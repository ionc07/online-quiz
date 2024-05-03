package com.online.quiz.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TestGroup extends AbstractEntity {
  private String name;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToMany
  @JoinTable(name = "test_group_test",
          joinColumns = @JoinColumn(name = "test_group_id"),
          inverseJoinColumns = @JoinColumn(name = "test_id"))
  private Set<Test> tests = new HashSet<>();

  public TestGroup() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Test> getTests() {
    return tests;
  }

  public void setTests(Set<Test> tests) {
    this.tests = tests;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
