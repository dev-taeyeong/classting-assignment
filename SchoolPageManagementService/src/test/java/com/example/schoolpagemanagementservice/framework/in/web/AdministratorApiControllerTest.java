package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.dto.AdministratorDto;
import com.example.schoolpagemanagementservice.application.usecase.CreateAdministratorUseCase;
import com.example.schoolpagemanagementservice.application.usecase.GetAllAdministratorsUseCase;
import com.example.schoolpagemanagementservice.framework.in.web.request.CreateAdministratorRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdministratorApiController.class)
class AdministratorApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateAdministratorUseCase createAdministratorUseCase;

    @MockBean
    private GetAllAdministratorsUseCase getAllAdministratorsUseCase;

    @DisplayName("관리자 생성 요청 시 관리자 DTO가 반환된다")
    @Test
    void givenAdministratorCreationRequest_whenCreating_thenReturnAdministratorDto() throws Exception {
        // given
        CreateAdministratorRequest request = new CreateAdministratorRequest("관리자");
        given(createAdministratorUseCase.createAdministrator(request.toCommand()))
                .willReturn(new AdministratorDto(1L, request.name()));

        // when & then
        mvc.perform(post("/api/v1/administrators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(request.name()))
                .andDo(print());
    }
}
