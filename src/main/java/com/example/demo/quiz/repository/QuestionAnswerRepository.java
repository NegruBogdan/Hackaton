package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Answer;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

    @Query("SELECT a FROM answers a WHERE a.question=?1 ")
    static Collection<QuestionAnswer> getAnswersFromQuestion(Question question) {
        return null;
    }

    @Query("SELECT a FROM answers a WHERE a.id=?1 ")
    QuestionAnswer getQuestionAnswerById(Long id);



}