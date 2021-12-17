package com.example.demo.appuser.repository;

import com.example.demo.appuser.model.Class_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class_, Long> {
    Class_ getById(Long id);
}
