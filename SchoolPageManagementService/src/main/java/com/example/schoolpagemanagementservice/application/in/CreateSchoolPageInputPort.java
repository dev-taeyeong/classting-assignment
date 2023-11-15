package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.out.CreateSchoolPageOutputPort;
import com.example.schoolpagemanagementservice.application.usecase.CreateSchoolPageUseCase;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateSchoolPageInputPort implements CreateSchoolPageUseCase {

    private final CreateSchoolPageOutputPort createSchoolPageOutputPort;

    @Override
    public SchoolPageDto createSchoolPage(CreateSchoolPageCommand command) {
        SchoolPage schoolPage = SchoolPage.createSchoolPage(command.administratorId(), command.location(), command.name());
        SchoolPage savedSchoolPage = createSchoolPageOutputPort.save(schoolPage);
        return SchoolPageDto.fromDomainModel(savedSchoolPage);
    }
}
