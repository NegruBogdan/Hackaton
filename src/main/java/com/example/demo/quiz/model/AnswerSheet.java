package com.example.demo.quiz.model;

import com.example.demo.appuser.model.Student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity(name="answer_sheets")
public class AnswerSheet implements Serializable {
    @Id
    @ManyToOne
    private Quiz quiz;
    @Id
    @ManyToOne
    private Student student;
    private int totalScore;
}
