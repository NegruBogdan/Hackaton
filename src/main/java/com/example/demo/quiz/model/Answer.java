package com.example.demo.quiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name="answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Question question;
    private String answerText;

    public Answer(Question question, String answerText) {
        this.question = question;
        this.answerText = answerText;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + question +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
