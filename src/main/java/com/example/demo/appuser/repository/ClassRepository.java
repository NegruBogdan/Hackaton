package com.example.demo.appuser.repository;

import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.model.Student;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class_, Long> {
    Class_ getById(Long id);
}
