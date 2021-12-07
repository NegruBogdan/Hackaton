package com.example.demo.appuser.model;

import java.util.Collection;

//@Entity
public class Class_ {
//    @Id
    private long id;
    private String name;
//    @OneToMany
    private Collection<Student> students;
//    @ManyToMany
    private Collection<Teacher> teachers;
}
