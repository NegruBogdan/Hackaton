package com.example.demo.appuser;

import javax.persistence.*;

@Entity
public class Student extends AppUser {
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    protected Long id;
    private Class_ class_;
}
