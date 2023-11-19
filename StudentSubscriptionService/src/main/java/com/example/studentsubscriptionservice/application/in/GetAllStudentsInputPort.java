package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.dto.StudentDto;
import com.example.studentsubscriptionservice.application.out.GetAllStudentsOutputPort;
import com.example.studentsubscriptionservice.application.usecase.GetAllStudentsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetAllStudentsInputPort implements GetAllStudentsUseCase {

    private final GetAllStudentsOutputPort getAllStudentsOutputPort;

    @Override
    public Page<StudentDto> getAllStudents(Pageable pageable) {
        return getAllStudentsOutputPort.fetchAll(pageable)
                .map(StudentDto::fromDomainModel);
    }
}
