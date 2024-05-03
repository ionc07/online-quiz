package com.online.quiz.repository;

import com.online.quiz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  User findUserByEmail(String email);

  Page<User> findAllBy(Pageable pageable);
}
