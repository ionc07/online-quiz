package com.online.quiz.repository;

import com.online.quiz.model.User;
import com.online.quiz.projection.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  UserDetails findUserByEmail(String email);
}
