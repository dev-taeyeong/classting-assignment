package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.command.CreateStudentCommand;
import com.example.studentsubscriptionservice.application.in.dto.StudentDto;
import com.example.studentsubscriptionservice.application.out.CreateStudentOutputPort;
import com.example.studentsubscriptionservice.domain.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CreateStudentInputPortTest {

    @InjectMocks
    private CreateStudentInputPort sut;

    @Mock
    private CreateStudentOutputPort createStudentOutputPort;

    @Test
    void createStudentTest() {
        // given
        CreateStudentCommand command = new CreateStudentCommand("student name");
        Student student = Student.createStudent(command.name());
        given(createStudentOutputPort.save(any(Student.class))).willReturn(student);

        // when
        StudentDto result = sut.createStudent(command);

        // then
        assertThat(result.name()).isEqualTo(command.name());
        then(createStudentOutputPort).should().save(any(Student.class));
    }
}