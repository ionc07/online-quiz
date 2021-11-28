package com.online.quiz.repository;

import com.online.quiz.model.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestRepository extends JpaRepository<UserTest, Long> {
}
