package com.online.quiz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

  @NotNull(message = "First name is mandatory")
  @Size(min = 2, message = "First name must not be less than 2 characters")
  private String firstName;

  @NotNull(message = "Last name is mandatory")
  @Size(min = 2, message = "Last name must not be less than 2 characters")
  private String lastName;

  @NotNull(message = "Email is mandatory")
  @Email(message = "Email must be well-formed")
  private String email;

  @NotNull(message = "Password is mandatory")
  @Size(min = 8, message = "Password must be greater than 7 characters!")
  private String password;

  private Date createdAt;

  @OneToOne(cascade = CascadeType.ALL)
  private Role role;

  private String resetCode;

  public User() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getResetCode() {
    return resetCode;
  }

  public void setResetCode(String resetCode) {
    this.resetCode = resetCode;
  }
}
