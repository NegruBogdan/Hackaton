package com.example.demo.appuser.model;

import com.example.demo.appuser.model.AppUser;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Teacher extends AppUser {
//    @ManyToMany
//    private Collection<Class_> classes;
    private int classes;
    public Teacher(String firstName,
                   String lastName,
                   String email,
                   String password) {
        super(firstName, lastName, email, password);
        this.classes = 2;
    }
}
