package com.online.quiz.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface UserDetails {

  Long getId();

  String getEmail();

  String getFirstName();

  String getLastName();

  Date getCreatedAt();

  @Value("#{target.role.name}")
  String getRole();
}
