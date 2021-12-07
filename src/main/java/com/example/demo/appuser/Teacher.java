package com.example.demo.appuser;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher extends AppUser{
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )

    private List<Class_> classes;
}
