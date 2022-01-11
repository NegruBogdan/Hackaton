package com.example.demo.quiz.controller;

import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.QuestionAnswer;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.request.QuestionRequest;
import com.example.demo.quiz.request.QuizRequest;
import com.example.demo.quiz.service.QuestionAnswerService;
import com.example.demo.quiz.service.QuestionService;
import com.example.demo.quiz.service.QuizService;
import com.example.demo.security.QuestionAnswerSession;
import com.example.demo.security.QuestionSession;
import com.example.demo.quiz.model.QuizSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

//controler pentru quiz standard
@RestController
@RequestMapping(path = "quizzes")
@AllArgsConstructor
public class QuizController {
    private final QuestionService questionService;
    private final QuizService quizService;
    private final QuestionAnswerService questionAnswerService;
    //private Long scor;

//    @Autowired
//    QuizSession quizSession;

    @Autowired
    QuestionAnswerSession questionAnswerSession;

    @Autowired
    QuestionSession questionSession;

    @PostMapping(value="/add")
    public ModelAndView addQuiz(@ModelAttribute QuizRequest request) {// @ModelAttribute
        Quiz quiz = quizService.addQuiz(request);
        return new ModelAndView("redirect:/quizzes/" + quiz.getId());
    }

    @PostMapping(value="{quizId}/addQuestion")
    public ModelAndView addQuestion(@PathVariable long quizId, @ModelAttribute QuestionRequest request) {// @ModelAttribute

        System.out.println("\n\n\n" + request + "\n\n\n");
        Question question = questionService.addQuestion(quizId, request);
        return new ModelAndView("redirect:/quizzes/" + quizId);
    }

    @GetMapping(value="{id}")
    public ModelAndView getQuizById(@PathVariable long id, Model model) {
        Quiz quiz = quizService.getQuizById(id);
        Collection<Question> questions = quizService.getQuestionsFromQuiz(id);
        questions.forEach(question -> {
            Collection<QuestionAnswer> questionAnswers = questionService.getAnswersFromQuestion(question.getId());
            question.setAnswers(questionAnswers);
        });
        model.addAttribute("questions", questions);
        model.addAttribute("quiz", quiz);
        return new ModelAndView("quiz");
//        try {
//            return quizService.getQuizById(id).toString();
//        } catch (EntityNotFoundException exception) {
//            return "Quiz not found.";
//        } catch (Exception exception) {
//            return "Something wrong happened" + exception.toString();
//        }
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

//    @GetMapping(value ="/startquiz")
//    public ModelAndView StartQuiz(String givenPassword, String quizName){
//        ModelAndView loginView = new ModelAndView("quiz.html");
//
//        Quiz quiz = quizService.loginQuiz(givenPassword,quizName);
//        if (quiz==null){
//            loginView.addObject("errQuizLogin", "Invalid Login Credentials");
//
//        }
//        else
//        {
//            quizSession.setId(quiz.getId());
//            return new ModelAndView("redirect:/quiz");
//        }
//        return loginView;
//    }

//    @PostMapping("/question/{id}")
//    public void QuestionUse(@PathVariable long id,Long score,@RequestBody Long userAnswer){
//        if(questionAnswerService.CheckAnswer(userAnswer))
//            score = score+ questionService.getScore(userAnswer);
//    }

//    @GetMapping("/question/{id}")
//    public Collection<QuestionAnswer> QuestionPage(@PathVariable long id, QuestionSession questionSession){
//        return  questionService.getAnswersFromQuestion(questionSession.getId());
//    }

//    @GetMapping("/quiz/{id}")
//    public Collection<Question> QuizInit(@PathVariable long id,QuizSession quizSession){
//        return quizService.getQuestionsFromQuiz(quizSession.getId());
//
//    }

}
