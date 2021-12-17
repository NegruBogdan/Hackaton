package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Answer;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT a FROM answers a WHERE a.question=?1")
    Collection<Answer> getAnswersFromQuestion(Question question);
}
