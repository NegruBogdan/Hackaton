package com.example.demo.quiz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="questions")
public class Question {
    @Id
    private Long id;
    @ManyToOne
    private Quiz quiz;
    @OneToOne
    private Answer correctAnswer;
    private String questionText;
    private int score;
}
