package com.example.studentsubscriptionservice.application.out;

import com.example.studentsubscriptionservice.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllStudentsOutputPort {

    Page<Student> fetchAll(Pageable pageable);
}
