package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.out.GetAllStudentsOutputPort;
import com.example.studentsubscriptionservice.domain.model.Student;
import com.example.studentsubscriptionservice.framework.out.jpa.StudentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllStudentsAdapter implements GetAllStudentsOutputPort {

    private final StudentJpaRepository studentJpaRepository;

    @Override
    public Page<Student> fetchAll(Pageable pageable) {
        return studentJpaRepository.findAll(pageable);
    }
}
