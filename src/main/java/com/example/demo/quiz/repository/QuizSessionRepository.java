package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
    QuizSession getById(Long id);
}
