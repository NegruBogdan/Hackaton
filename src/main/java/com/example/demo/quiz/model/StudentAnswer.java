package com.example.demo.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="students_answers")
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private QuestionAnswer choice;
//    @ManyToOne
//    private Question question;
    @ManyToOne
    private QuizSession quizSession;

    public StudentAnswer(QuestionAnswer choice, QuizSession quizSession) {
        this.choice = choice;
        this.quizSession = quizSession;
    }
}
