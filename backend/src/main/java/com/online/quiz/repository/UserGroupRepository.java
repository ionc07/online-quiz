package com.online.quiz.repository;

import com.online.quiz.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
  List<UserGroup> findAllByUserId(Long userId);
}
