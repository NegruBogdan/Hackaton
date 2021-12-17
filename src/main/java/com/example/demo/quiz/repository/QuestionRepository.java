package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
