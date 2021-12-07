package com.example.demo.appuser.model;

import com.example.demo.appuser.model.AppUser;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Entity(name="teachers")
public class Teacher extends AppUser {
    @ManyToMany
    private Collection<Class_> classes;
//    private int classes;

    public Teacher(String firstName,
                   String lastName,
                   String email,
                   String password) {
        super(firstName, lastName, email, password);
        this.classes = null;
    }
}
