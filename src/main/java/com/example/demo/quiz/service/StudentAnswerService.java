package com.example.demo.quiz.service;

import com.example.demo.quiz.model.StudentAnswer;
import com.example.demo.quiz.request.StudentAnswerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentAnswerService {
    public StudentAnswer addStudentAnswer(StudentAnswerRequest request) {
        // TO DO
        StudentAnswer studentAnswer = new StudentAnswer();
        return studentAnswer;
    }
}
