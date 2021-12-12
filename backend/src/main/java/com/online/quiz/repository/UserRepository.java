package com.online.quiz.repository;

import com.online.quiz.model.User;
import com.online.quiz.projection.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  UserDetails findUserByEmail(String email);
}
