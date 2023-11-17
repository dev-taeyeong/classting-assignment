package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.command.CreateStudentCommand;
import com.example.studentsubscriptionservice.application.in.dto.StudentDto;
import com.example.studentsubscriptionservice.application.out.CreateStudentOutputPort;
import com.example.studentsubscriptionservice.application.usecase.CreateStudentUseCase;
import com.example.studentsubscriptionservice.domain.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateStudentInputPort implements CreateStudentUseCase {

    private final CreateStudentOutputPort createStudentOutputPort;

    @Override
    public StudentDto createStudent(CreateStudentCommand command) {
        Student student = Student.createStudent(command.name());
        createStudentOutputPort.save(student);

        return StudentDto.fromDomainModel(student);
    }
}
