package com.example.demo.security;

import com.example.demo.quiz.service.QuizService;
import org.springframework.stereotype.Service;

@Service
public class QuizSession {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
