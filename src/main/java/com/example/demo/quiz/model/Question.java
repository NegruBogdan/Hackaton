package com.example.demo.quiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    @OneToOne
    private Answer correctAnswer;
    private String questionText;
    private int score;

    public Question(Quiz quiz, String questionText, int score) {
        this.quiz = quiz;
        this.correctAnswer = null;
        this.questionText = questionText;
        this.score = score;
    }

    @Override
    public String toString() {
        if (correctAnswer != null) {
            return "Question{" +
                    "id=" + id +
                    ", quiz=" + quiz +
                    ", correctAnswerId=" + correctAnswer.getId().toString() +
                    ", questionText='" + questionText + '\'' +
                    ", score=" + score +
                    '}';
        } else {
            return "Question{" +
                    "id=" + id +
                    ", quiz=" + quiz +
                    ", correctAnswer=null" +
                    ", questionText='" + questionText + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
