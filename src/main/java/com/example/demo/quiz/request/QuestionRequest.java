package com.example.demo.quiz.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionRequest {
    private final String questionText;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final String answer4;
    private final int correctAnswer;
    private final int score;
}
