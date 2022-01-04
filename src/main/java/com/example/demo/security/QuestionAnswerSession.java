package com.example.demo.security;

import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerSession {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
