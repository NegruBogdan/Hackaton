package com.example.demo.quiz.service;

import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.quiz.model.Answer;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.repository.QuestionRepository;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.quiz.request.QuestionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final AnswerService answerService;

    public Question addQuestion(Long quizId, QuestionRequest request) {
        Quiz quiz = quizRepository.getById(quizId);
        Question question = new Question(quiz, request.getQuestionText(), request.getScore());
        questionRepository.save(question);
        Answer answer1 = answerService.addAnswer(question, request.getAnswer1());
        Answer answer2 = answerService.addAnswer(question, request.getAnswer2());
        Answer answer3 = answerService.addAnswer(question, request.getAnswer3());
        Answer answer4 = answerService.addAnswer(question, request.getAnswer4());
        switch (request.getCorrectAnswer()) {
            case 1:
                question.setCorrectAnswer(answer1);
                break;
            case 2:
                question.setCorrectAnswer(answer2);
                break;
            case 3:
                question.setCorrectAnswer(answer3);
                break;
            case 4:
                question.setCorrectAnswer(answer4);
                break;
        }
        questionRepository.save(question);
        return question;
    }
}
