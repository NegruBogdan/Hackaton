package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
