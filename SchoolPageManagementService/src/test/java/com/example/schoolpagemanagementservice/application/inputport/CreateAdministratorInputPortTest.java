package com.example.schoolpagemanagementservice.application.inputport;

import com.example.schoolpagemanagementservice.application.in.CreateAdministratorCommand;
import com.example.schoolpagemanagementservice.application.in.CreateAdministratorInputPort;
import com.example.schoolpagemanagementservice.application.out.CreateAdministratorOutputPort;
import com.example.schoolpagemanagementservice.domain.model.Administrator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        String name = "test administrator";
        CreateAdministratorCommand command = new CreateAdministratorCommand(name);
        Administrator administrator = new Administrator(1L, name);
        given(createAdministratorOutputPort.save(any())).willReturn(administrator);

        // when
        sut.createAdministrator(command);

        // then
        then(createAdministratorOutputPort).should().save(any());
    }
}
