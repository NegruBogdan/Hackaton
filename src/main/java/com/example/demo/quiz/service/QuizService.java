package com.example.demo.quiz.service;

import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.appuser.service.ClassService;
import com.example.demo.appuser.service.StudentService;
import com.example.demo.appuser.service.TeacherService;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.model.QuizSession;
import com.example.demo.quiz.model.StudentAnswer;
import com.example.demo.quiz.repository.QuestionRepository;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.quiz.repository.QuizSessionRepository;
import com.example.demo.quiz.request.QuizRequest;
import com.example.demo.quiz.request.StudentAnswerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizSessionRepository quizSessionRepository;
    private final QuestionRepository questionRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;
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

    public Quiz publishQuiz(Long id) {
        Quiz quiz = quizRepository.getById(id);
        quiz.setPublished(true);
        quizRepository.save(quiz);
        return quiz;
    }

    public QuizSession attemptQuiz(Quiz quiz, Student student) {
        QuizSession quizSession = new QuizSession(quiz, student);
        quizSessionRepository.save(quizSession);
        return quizSession;
    }

    public QuizSession getQuizSessionById(Long id) {
        return quizSessionRepository.getById(id);
    }

    public int calculateScore(QuizSession quizSession) {
        return 0;
    }

    public Collection<Question> getQuestionsFromQuiz(Long id) {
        return questionRepository.getQuestionFromQuiz(quizRepository.getById(id));
    }

    public Collection<Quiz> getQuizzesByAuthorId(Long teacherId) {
        return quizRepository.getQuizzesByAuthor(teacherService.getTeacherById(teacherId));
    }

    public Collection<Quiz> getQuizzesByStudentId(Long studentId) {
        return quizRepository.getQuizzesByStudent(studentService.getStudentById(studentId));
    }




//    public Quiz loginQuiz(String password,String name){
//        return quizRepository.QuizLogin(password,name);
//    }
}
