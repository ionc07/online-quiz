package com.online.quiz.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
//TODO: change equalsAndHashCOde
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity implements UserDetails {

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  @ManyToMany
  @JoinTable(
          name = "user_shared_test",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "test_id")
  )
  private List<Test> tests = new ArrayList<>();

  private LocalDateTime createdAt;

  @OneToOne
  private Role role;

  private String resetCode;

  public User() {
  }

  @PrePersist
  public void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(role.getName()));
    return roles;
  }

  public List<Test> getTests() {
    return tests;
  }

  public void setTests(List<Test> tests) {
    this.tests = tests;
  }

  public void addTests(List<Test> tests) {
    this.tests.addAll(tests);
  }

  public void addTest(Test test) {
    this.tests.add(test);
  }

  @Override
  public String getUsername() {
    return this.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
