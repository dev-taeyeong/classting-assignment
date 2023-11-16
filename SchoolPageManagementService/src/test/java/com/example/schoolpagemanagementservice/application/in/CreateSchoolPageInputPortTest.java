package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.in.dto.CreateSchoolPageCommand;
import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;
import com.example.schoolpagemanagementservice.application.out.CreateSchoolPageOutputPort;
import com.example.schoolpagemanagementservice.domain.model.Administrator;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CreateSchoolPageInputPortTest {

    @InjectMocks
    private CreateSchoolPageInputPort sut;

    @Mock
    private CreateSchoolPageOutputPort createSchoolPageOutputPort;

    @Test
    void createSchoolPageTest() {
        // given
        CreateSchoolPageCommand command = new CreateSchoolPageCommand(1L, "test location", "test name");

        SchoolPage schoolPage = SchoolPage.createSchoolPage(command.administratorId(), command.location(), command.name());
        given(createSchoolPageOutputPort.save(any(SchoolPage.class))).willReturn(schoolPage);
        given(createSchoolPageOutputPort.fetchAdministratorById(anyLong())).willReturn(Optional.of(Administrator.createAdministrator("test name")));

        // when
        SchoolPageDto result = sut.createSchoolPage(command);

        // then
        assertThat(result.administratorId()).isEqualTo(command.administratorId());
        assertThat(result.location()).isEqualTo(command.location());
        assertThat(result.name()).isEqualTo(command.name());

        then(createSchoolPageOutputPort).should().save(any(SchoolPage.class));
        then(createSchoolPageOutputPort).should().fetchAdministratorById(command.administratorId());
    }
}