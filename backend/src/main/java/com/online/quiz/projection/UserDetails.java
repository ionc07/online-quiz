package com.online.quiz.projection;

import java.util.Date;

public interface UserDetails {

  String getEmail();

  String getFirstName();

  String getLastName();

  Date getCreatedAt();
}
