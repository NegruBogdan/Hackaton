package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    StudentAnswer getById(Long id);
}
