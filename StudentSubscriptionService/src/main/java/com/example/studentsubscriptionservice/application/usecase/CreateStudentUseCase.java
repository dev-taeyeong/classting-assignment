package com.example.studentsubscriptionservice.application.usecase;

import com.example.studentsubscriptionservice.application.in.CreateStudentCommand;
import com.example.studentsubscriptionservice.application.in.StudentDto;

public interface CreateStudentUseCase {

    StudentDto createStudent(CreateStudentCommand command);
}
