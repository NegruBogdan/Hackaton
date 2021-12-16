package com.example.demo.quiz.model;

import com.example.demo.quiz.model.Answer;
import com.example.demo.quiz.model.AnswerSheet;
import com.example.demo.quiz.model.Question;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="student_answers")
public class StudentAnswer {
    @Id
    private Long id;
    @ManyToOne
    private AnswerSheet answerSheet;
    @ManyToOne
    private Question question;
    @ManyToOne
    private Answer answer;
}
