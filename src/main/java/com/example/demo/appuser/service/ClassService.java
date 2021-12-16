package com.example.demo.appuser.service;

import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.repository.ClassRepository;
import com.example.demo.appuser.request.ClassRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;

    public String addClass(ClassRequest request) {
        Class_ class_ = new Class_(request.getClassName());
        classRepository.save(class_);
        return "class added";
    }

    public Class_ getClassById(Long id){
        return classRepository.getById(id);
    }
}
