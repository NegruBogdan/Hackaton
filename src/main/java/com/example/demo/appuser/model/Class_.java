package com.example.demo.appuser.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Entity(name="classes")
@ToString
public class Class_ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Class_(String name) {
        this.name = name;
    }
}
