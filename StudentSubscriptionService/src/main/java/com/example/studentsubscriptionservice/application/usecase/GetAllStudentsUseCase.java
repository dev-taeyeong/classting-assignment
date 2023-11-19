package com.example.studentsubscriptionservice.application.usecase;

import com.example.studentsubscriptionservice.application.in.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllStudentsUseCase {

    Page<StudentDto> getAllStudents(Pageable pageable);
}
