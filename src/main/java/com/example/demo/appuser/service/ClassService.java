package com.example.demo.appuser.service;

import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.repository.ClassRepository;
import com.example.demo.appuser.repository.StudentRepository;
import com.example.demo.appuser.request.ClassRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    public String addClass(ClassRequest request) {
        Class_ class_ = new Class_(request.getClassName());
        classRepository.save(class_);
        return "class added";
    }

    public Class_ getClassById(Long id){
        return classRepository.getById(id);
    }

    public Collection<Student> getAllStudentsByClassId(Long classId){
        return studentRepository.getAllStudentsByClassId(classRepository.getById(classId));
    };
}
