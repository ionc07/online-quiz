package com.online.quiz.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserGroup extends AbstractEntity {
  private String name;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToMany
  @JoinTable(name = "user_group_user",
          joinColumns = @JoinColumn(name = "user_group_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users = new ArrayList<>();

  public UserGroup() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public void addUsers(List<User> users) {
    this.users.addAll(users);
  }

  public void addUser(User user) {
    this.users.add(user);
  }

}
