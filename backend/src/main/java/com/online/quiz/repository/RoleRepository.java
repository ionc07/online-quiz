package com.online.quiz.repository;

import com.online.quiz.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

  Role findByName(String name);
}
