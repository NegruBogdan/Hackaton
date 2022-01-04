package com.example.demo.quiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name="studentsAnswers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private QuestionAnswer choice;
    @ManyToOne
    private Question question;





}
