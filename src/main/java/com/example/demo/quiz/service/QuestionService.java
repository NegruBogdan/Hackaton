package com.example.demo.quiz.service;

import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.QuestionAnswer;
import com.example.demo.quiz.repository.QuestionAnswerRepository;
import com.example.demo.quiz.repository.QuestionRepository;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.quiz.request.QuestionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Service
@AllArgsConstructor
public class QuestionService {
    private static QuestionRepository questionRepository;
    private static QuestionAnswerRepository answerRepository;
    private final QuizRepository quizRepository;
    private final QuestionAnswerService answerService;

    public Question addQuestion(long quizId, QuestionRequest request) {
        return null;
    }

    public Question getQuestionById(@PathVariable long id) {
        return questionRepository.getById(id);
    }

    static public Collection<QuestionAnswer> getAnswersFromQuestion(Long id) {
        return QuestionAnswerRepository.getAnswersFromQuestion(questionRepository.getById(id));
    }

    public Long getScore(Long id){
        return questionRepository.getScore(id);
    }

}
