package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.out.CreateStudentOutputPort;
import com.example.studentsubscriptionservice.domain.model.Student;
import com.example.studentsubscriptionservice.framework.out.jpa.StudentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateStudentAdapter implements CreateStudentOutputPort {

    private final StudentJpaRepository studentJpaRepository;

    @Override
    public Student save(Student student) {
        return studentJpaRepository.save(student);
    }
}
