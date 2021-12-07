package com.example.demo.appuser.model;

import com.example.demo.appuser.model.AppUser;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity(name="students")
public class Student extends AppUser {

    @ManyToOne
    private Class_ class_;
//    private int class_;

    public Student(String firstName,
                   String lastName,
                   String email,
                   String password) {
        super(firstName, lastName, email, password);
        this.class_ = null;
    }

}
