package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.in.dto.AdministratorDto;
import com.example.schoolpagemanagementservice.application.in.dto.CreateAdministratorCommand;
import com.example.schoolpagemanagementservice.application.out.CreateAdministratorOutputPort;
import com.example.schoolpagemanagementservice.domain.model.Administrator;
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
class CreateAdministratorInputPortTest {

    @InjectMocks
    private CreateAdministratorInputPort sut;

    @Mock
    private CreateAdministratorOutputPort createAdministratorOutputPort;

    @Test
    void createAdministratorTest() {
        // given
        long administratorId = 1L;
        String name = "test administrator";
        CreateAdministratorCommand command = new CreateAdministratorCommand(name);
        Administrator administrator = new Administrator(administratorId, name);
        given(createAdministratorOutputPort.save(any())).willReturn(administrator);

        // when
        AdministratorDto result = sut.createAdministrator(command);

        // then
        assertThat(result).isEqualTo(new AdministratorDto(administratorId, name));
        then(createAdministratorOutputPort).should().save(any());
    }
}
