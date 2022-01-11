package com.example.demo.quiz.controller;

import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
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
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//controler pentru quiz standard
@RestController
@RequestMapping(path = "quizzes")
@AllArgsConstructor
public class QuizController {
    private final QuestionService questionService;
    private final QuizService quizService;
    private final QuestionAnswerService questionAnswerService;
    // private Long scor;

//    @Autowired
//    QuizSession quizSession;

//    @Autowired
//    QuestionAnswerSession questionAnswerSession;
//
//    @Autowired
//    QuestionSession questionSession;

    @PostMapping(value="/add")
    public ModelAndView addQuiz(@ModelAttribute QuizRequest request) {// @ModelAttribute
        Quiz quiz = quizService.addQuiz(request);
        return new ModelAndView("redirect:/quizzes/" + quiz.getId());
    }

    @PostMapping(value="{quizId}/addQuestion")
    public ModelAndView addQuestion(@PathVariable long quizId, @ModelAttribute QuestionRequest request) {
        Question question = questionService.addQuestion(quizId, request);
        return new ModelAndView("redirect:/quizzes/" + quizId + "/edit");
    }

    @PostMapping(value="{quizId}/publishQuiz")
    public ModelAndView publishQuiz(@PathVariable long quizId) {
        Quiz quiz = quizService.publishQuiz(quizId);
        return new ModelAndView("redirect:/quizzes/" + quizId);
    }

    @GetMapping(value="{id}/edit")
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

    @GetMapping(value="{id}")
    public ModelAndView viewQuizById(@PathVariable long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Quiz quiz = quizService.getQuizById(id);
        if (auth.getPrincipal() instanceof Teacher) {
            model.addAttribute("role", "teacher");
        } else {
            model.addAttribute("role", "student");
        }
        model.addAttribute("quiz", quiz);
        return new ModelAndView("view_quiz");
//        try {
//            return quizService.getQuizById(id).toString();
//        } catch (EntityNotFoundException exception) {
//            return "Quiz not found.";
//        } catch (Exception exception) {
//            return "Something wrong happened" + exception.toString();
//        }
    }

    @PostMapping("{id}/attemptQuiz")
    public ModelAndView attemptQuiz(@PathVariable long id, Model model) {
        Quiz quiz = quizService.getQuizById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = (Student) auth.getPrincipal();
        Collection<Question> questions = quizService.getQuestionsFromQuiz(id);
        QuizSession quizSession = quizService.attemptQuiz(quiz, student);
        return new ModelAndView("redirect:/quizzes/" + quizSession.getId() + "/question/" + questions.iterator().next().getId());
    }

    @GetMapping(value="{quizSession_id}/question/{question_id}")
    public ModelAndView viewQuestionFromQuiz(@PathVariable long quizSession_id, @PathVariable long question_id, Model model) {
        QuizSession quizSession = quizService.getQuizSessionById(quizSession_id);
        Quiz quiz = quizSession.getQuiz();
        Collection<Question> questions = quizService.getQuestionsFromQuiz(quiz.getId());
        ArrayList<Long> questionIds = new ArrayList<>();
        questions.forEach(question -> {
            questionIds.add(question.getId());
        });
        int pos = questionIds.indexOf(question_id);
        if (pos < questionIds.size() - 1) {
            model.addAttribute("nextQuestionId", questionIds.get(pos + 1));
        } else {
            model.addAttribute("nextQuestionId", -1);
        }
        if (pos > 0) {
            model.addAttribute("previousQuestionId", questionIds.get(pos - 1));
        } else {
            model.addAttribute("previousQuestionId", -1);
        }
        Question question = questionService.getQuestionById(question_id);
        Collection<QuestionAnswer> answers = questionService.getAnswersFromQuestion(question_id);
        question.setAnswers(answers);
        model.addAttribute("question", question);
        model.addAttribute("quiz", quiz);
        return new ModelAndView("solve_question");
    }

    @PostMapping(value="{quizSession_id}/question/{question_id}")
    public ModelAndView sendQuestionAnswer(@PathVariable long quizSession_id, @PathVariable long question_id,
                                           @ModelAttribute QuestionRequest request, Model model) {
        return new ModelAndView("solve_question");
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
