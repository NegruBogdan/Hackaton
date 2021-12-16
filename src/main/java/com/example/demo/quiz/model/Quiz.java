package com.example.demo.quiz.model;

import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity(name="quizzes")
public class Quiz {
    @Id
    private Long id;
    @ManyToOne
    private Teacher author;
    private String name;
    @ManyToMany
    private Collection<Student> studentsEnrolled;
}
