package com.example.demo.quiz.service;

import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.appuser.service.ClassService;
import com.example.demo.appuser.service.TeacherService;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.repository.QuestionRepository;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.quiz.request.QuizRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final TeacherService teacherService;
    private final ClassService classService;

    public Quiz addQuiz(QuizRequest request) {
        Teacher author = teacherService.getTeacherById(request.getAuthorId());
        Quiz quiz = new Quiz(author, request.getName());
        Collection<Student> students = classService.getAllStudentsByClassId(request.getClassId());
        quiz.setStudentsEnrolled(students);
        quizRepository.save(quiz);
        return quiz;
    }

    public Quiz getQuizById (Long id) {
        return quizRepository.getById(id);
    }

    public Collection<Question> getQuestionsFromQuiz(Long id) {
        return questionRepository.getQuestionFromQuiz(quizRepository.getById(id));
    }
}
