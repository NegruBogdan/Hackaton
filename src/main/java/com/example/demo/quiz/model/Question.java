package com.example.demo.quiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Setter
@Getter
@Entity(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Quiz quiz;
    private String questionText;
    private int score;
    @OneToMany
    private Collection<QuestionAnswer> answers;

    public Question(Quiz quiz,String questionText,int score){
        this.quiz = quiz;
        this.questionText = questionText;
        this.score = score;
    }
}
