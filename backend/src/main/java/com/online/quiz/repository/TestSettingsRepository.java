package com.online.quiz.repository;

import com.online.quiz.model.TestSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestSettingsRepository extends JpaRepository<TestSettings, Long> {
}
