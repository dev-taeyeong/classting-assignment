package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.in.dto.CreateSchoolPageCommand;
import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;
import com.example.schoolpagemanagementservice.application.out.CreateSchoolPageOutputPort;
import com.example.schoolpagemanagementservice.application.usecase.CreateSchoolPageUseCase;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateSchoolPageInputPort implements CreateSchoolPageUseCase {

    private final CreateSchoolPageOutputPort createSchoolPageOutputPort;

    @Override
    public SchoolPageDto createSchoolPage(CreateSchoolPageCommand command) {
        validateAdministratorExists(command.administratorId());
        SchoolPage schoolPage = SchoolPage.createSchoolPage(command.administratorId(), command.location(), command.name());
        SchoolPage savedSchoolPage = createSchoolPageOutputPort.save(schoolPage);
        return SchoolPageDto.fromDomainModel(savedSchoolPage);
    }

    private void validateAdministratorExists(long administratorId) {
        createSchoolPageOutputPort.fetchAdministratorById(administratorId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 관리자입니다."));
    }
}
