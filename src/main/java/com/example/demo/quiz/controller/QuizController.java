package com.example.demo.quiz.controller;

import com.example.demo.quiz.model.Answer;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.QuestionAnswer;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.repository.QuizRepository;
import com.example.demo.quiz.request.QuestionRequest;
import com.example.demo.quiz.request.QuizRequest;
import com.example.demo.quiz.service.QuestionAnswerService;
import com.example.demo.quiz.service.QuestionService;
import com.example.demo.quiz.service.QuizService;
import com.example.demo.security.QuestionAnswerSession;
import com.example.demo.security.QuestionSession;
import com.example.demo.security.QuizSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
//controler pentru quiz standard
@RestController
@RequestMapping(path = "quizzes")
@AllArgsConstructor
public class QuizController {
    private final QuestionService questionService;
    private final QuizService quizService;
    private final QuestionAnswerService questionAnswerService;
    //private Long scor;

    @Autowired
    QuizSession quizSession;

    @Autowired
    QuestionAnswerSession questionAnswerSession;

    @Autowired
    QuestionSession questionSession;

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

    @GetMapping(value="{id}/questions")
    public String getQuestionsFromQuiz(@PathVariable long id) {
        try {
            return quizService.getQuestionsFromQuiz(id).toString();
        } catch (EntityNotFoundException exception) {
            return "Quiz not found.";
        } catch (Exception exception) {
            return "Something wrong happened" + exception.toString();
        }
    }

    @GetMapping(value="/questions/{id}")
    public String getQuestionById(@PathVariable long id) {
        try {
            return questionService.getQuestionById(id).toString();
        } catch (EntityNotFoundException exception) {
            return "Question not found.";
        } catch (Exception exception) {
            return "Something wrong happened" + exception.toString();
        }
    }

    @GetMapping(value="/questions/{id}/answers")
    public String getAnswersFromQuestion(@PathVariable long id) {
        try {
            return questionService.getAnswersFromQuestion(id).toString();
        } catch (EntityNotFoundException exception) {
            return "Question not found.";
        } catch (Exception exception) {
            return "Something wrong happened" + exception.toString();
        }
    }

    @GetMapping(value ="/startquiz")
    public ModelAndView StartQuiz(String givenPassword, String quizName){
        ModelAndView loginView = new ModelAndView("quiz.html");

        Quiz quiz = quizService.loginQuiz(givenPassword,quizName);
        if (quiz==null){
            loginView.addObject("errQuizLogin", "Invalid Login Credentials");

        }
        else
        {
            quizSession.setId(quiz.getId());
            return new ModelAndView("redirect:/quiz");
        }
        return loginView;
    }

    @PostMapping("/question/{id}")
    public void QuestionUse(@PathVariable long id,Long score,@RequestBody Long userAnswer){
        if(questionAnswerService.CheckAnswer(userAnswer))
            score = score+ questionService.getScore(userAnswer);
    }

    @GetMapping("/question/{id}")
    public Collection<QuestionAnswer> QuestionPage(@PathVariable long id, QuestionSession questionSession){
        return  questionService.getAnswersFromQuestion(questionSession.getId());

    }


    @GetMapping("/quiz/{id}")
    public Collection<Question> QuizInit(@PathVariable long id,QuizSession quizSession){
        return quizService.getQuestionsFromQuiz(quizSession.getId());

    }




}
