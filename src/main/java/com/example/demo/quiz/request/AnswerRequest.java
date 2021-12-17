package com.example.demo.quiz.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AnswerRequest {
    private final QuestionRequest questionRequest;
    private final String answerText;
}
