package com.example.demo.quiz.service;

import com.example.demo.quiz.model.Answer;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.repository.AnswerRepository;
import com.example.demo.quiz.request.AnswerRequest;
import com.example.demo.quiz.request.QuestionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerService {
    AnswerRepository answerRepository;

    public Answer addAnswer(Question question, String answerText) {
        Answer answer = new Answer(question, answerText);
        answerRepository.save(answer);
        return answer;
    }
}
