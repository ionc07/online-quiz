package com.online.quiz.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

  private Date createdAt;

  @OneToOne
  private Role role;

  private String resetCode;

  public User() {
  }

  @PrePersist
  public void setCreatedAt() {
    this.createdAt = new Date();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(role.getName()));
    return roles;
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
