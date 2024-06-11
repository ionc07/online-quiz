package com.online.quiz.repository;

import com.online.quiz.model.AnswerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerTypeRepository extends JpaRepository<AnswerType, Long> {
}