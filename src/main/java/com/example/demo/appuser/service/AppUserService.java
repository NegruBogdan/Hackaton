package com.example.demo.appuser.service;

import com.example.demo.appuser.model.AppUser;
import com.example.demo.appuser.repository.AppUserInterface;
import com.example.demo.appuser.repository.StudentRepository;
import com.example.demo.appuser.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;

    public AppUser getByEmailAndPassword(String email, String password) {
        boolean userExists = studentRepository
                .findByEmail(email)
                .isPresent();

        if (userExists) {
            System.out.println(studentRepository.findByEmail(email).get().toString());
            return studentRepository.findByEmail(email).get();
        }

        userExists = teacherRepository
                .findByEmail(email)
                .isPresent();
        System.out.println("\n\n\n\n userExists\n\n\n\n\n");

        if (userExists) {
            System.out.println(studentRepository.findByEmail(email).get().toString());
            return teacherRepository.findByEmail(email).get();
        }

        return null;
    }
}
