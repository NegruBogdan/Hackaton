package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz getById(Long id);

    @Query(value ="SELECT q FROM  quizzes q where q.quizPassword=1? AND q.name=2?",
    nativeQuery = true)
    Quiz QuizLogin(String password,String name);


}
