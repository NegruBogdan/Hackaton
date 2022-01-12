package com.example.demo.quiz.service;

import com.example.demo.quiz.model.*;
import com.example.demo.quiz.repository.StudentAnswerRepository;
import com.example.demo.quiz.request.StudentAnswerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class StudentAnswerService {
    private final QuestionService questionService;
    private final StudentAnswerRepository studentAnswerRepository;

    public void addStudentAnswer(StudentAnswerRequest request, QuizSession quizSession, Question question) {
        Collection<QuestionAnswer> correctAnswers = questionService.getAnswersFromQuestion(question.getId());
        Iterator<QuestionAnswer> it = correctAnswers.iterator();
        StudentAnswer studentAnswer0 = new StudentAnswer(it.next(), quizSession, request.isAnswerCorrect0());
        studentAnswerRepository.save(studentAnswer0);
        StudentAnswer studentAnswer1 = new StudentAnswer(it.next(), quizSession, request.isAnswerCorrect1());
        studentAnswerRepository.save(studentAnswer1);
        StudentAnswer studentAnswer2 = new StudentAnswer(it.next(), quizSession, request.isAnswerCorrect2());
        studentAnswerRepository.save(studentAnswer2);
        StudentAnswer studentAnswer3 = new StudentAnswer(it.next(), quizSession, request.isAnswerCorrect3());
        studentAnswerRepository.save(studentAnswer3);
    }

    public Collection<StudentAnswer> getAnswersFromQuestionFromQuizSession(QuizSession quizSession, Question question) {
        Collection<StudentAnswer> studentAnswers = studentAnswerRepository.getAnswersFromQuizSession(quizSession);
        studentAnswers.removeIf(studentAnswer -> !studentAnswer.getAnswer().getQuestion().equals(question));
        return studentAnswers;
    }
}
