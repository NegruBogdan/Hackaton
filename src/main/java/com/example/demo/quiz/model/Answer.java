package com.example.demo.quiz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="answers")
public class Answer {
    @Id
    private Long id;
    @ManyToOne
    private Question question;
    private String answerText;
}
