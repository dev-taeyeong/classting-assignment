package com.example.studentsubscriptionservice.application.usecase;

import com.example.studentsubscriptionservice.application.in.command.CreateStudentCommand;
import com.example.studentsubscriptionservice.application.in.dto.StudentDto;

public interface CreateStudentUseCase {

    StudentDto createStudent(CreateStudentCommand command);
}
