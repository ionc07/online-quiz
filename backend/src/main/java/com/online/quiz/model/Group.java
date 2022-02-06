package com.online.quiz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group extends AbstractEntity {
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JoinColumn(name = "group_id")
  private List<TestGroup> testGroups;

  @OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JoinColumn(name = "group_id")
  private List<UserGroup> userGroups;

  public List<TestGroup> getTestGroups() {
    return testGroups;
  }

  public void setTestGroups(List<TestGroup> testGroups) {
    this.testGroups = testGroups;
  }

  public List<UserGroup> getUserGroups() {
    return userGroups;
  }

  public void setUserGroups(List<UserGroup> userGroups) {
    this.userGroups = userGroups;
  }
}
