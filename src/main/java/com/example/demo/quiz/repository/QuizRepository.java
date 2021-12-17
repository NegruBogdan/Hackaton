package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz getById(Long id);
}
