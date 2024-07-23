package com.Learning.SCEP_Backend.repository;

import com.Learning.SCEP_Backend.entity.Student;
import com.Learning.SCEP_Backend.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByRole(Roles role);
}
