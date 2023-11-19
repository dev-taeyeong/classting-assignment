package com.example.studentsubscriptionservice.framework.out.jpa;

import com.example.studentsubscriptionservice.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
}
