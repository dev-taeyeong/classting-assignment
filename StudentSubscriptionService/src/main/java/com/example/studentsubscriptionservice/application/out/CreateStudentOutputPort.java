package com.example.studentsubscriptionservice.application.out;

import com.example.studentsubscriptionservice.domain.model.Student;

public interface CreateStudentOutputPort {

    Student save(Student student);
}
