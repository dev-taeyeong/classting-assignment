package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.out.CreateSchoolPageOutputPort;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
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
class CreateSchoolPageInputPortTest {

    @InjectMocks
    private CreateSchoolPageInputPort sut;

    @Mock
    private CreateSchoolPageOutputPort createSchoolPageOutputPort;

    @Test
    void createSchoolPageTest() {
        // given
        long schoolPageId = 1L;
        long administratorId = 1L;
        String location = "test location";
        String name = "test name";
        CreateSchoolPageCommand command = new CreateSchoolPageCommand(administratorId, location, name);

        SchoolPage schoolPage = new SchoolPage(schoolPageId, administratorId, location, name);
        given(createSchoolPageOutputPort.save(any())).willReturn(schoolPage);

        // when
        SchoolPageDto result = sut.createSchoolPage(command);

        // then
        assertThat(result).isEqualTo(new SchoolPageDto(schoolPageId, administratorId, location, name));
        then(createSchoolPageOutputPort).should().save(any());
    }
}