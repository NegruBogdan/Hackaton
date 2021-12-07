package com.example.demo.appuser.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity(name="classes")
public class Class_ {
    @Id
    private long id;
    private String name;
//    @OneToMany
//    private Collection<Student> students;
//    @ManyToMany
//    private Collection<Teacher> teachers;
}
