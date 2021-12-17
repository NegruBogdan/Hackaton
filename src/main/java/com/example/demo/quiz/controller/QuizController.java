package com.example.demo.quiz.controller;

import com.example.demo.appuser.request.ClassRequest;
import com.example.demo.quiz.request.QuestionRequest;
import com.example.demo.quiz.request.QuizRequest;
import com.example.demo.quiz.service.QuestionService;
import com.example.demo.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="quizzes")
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final QuestionService questionService;

    @PostMapping(value="/add")
    public String addQuiz(@RequestBody QuizRequest request) {// @ModelAttribute
        return quizService.addQuiz(request).toString();
    }

    @PostMapping(value="{quizId}/addQuestion")
    public String addQuestion(@PathVariable long quizId, @RequestBody QuestionRequest request) {// @ModelAttribute
        return questionService.addQuestion(quizId, request).toString();
    }

    @GetMapping(value="{id}")
    public String getQuizById(@PathVariable long id) {
        try {
            return quizService.getQuizById(id).toString();
        } catch (EntityNotFoundException exception) {
            return "Quiz not found.";
        } catch (Exception exception) {
            return "Something wrong happened" + exception.toString();
        }
    }
}
