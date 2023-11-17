package com.example.studentsubscriptionservice.framework.in.web;

import com.example.studentsubscriptionservice.application.in.dto.StudentDto;
import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;
import com.example.studentsubscriptionservice.application.usecase.CreateStudentUseCase;
import com.example.studentsubscriptionservice.application.usecase.GetAllStudentsUseCase;
import com.example.studentsubscriptionservice.application.usecase.GetAllSubscriptionsByStudentIdUseCase;
import com.example.studentsubscriptionservice.framework.in.web.request.CreateStudentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentApiController.class)
class StudentApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateStudentUseCase createStudentUseCase;

    @MockBean
    private GetAllStudentsUseCase getAllStudentsUseCase;

    @MockBean
    private GetAllSubscriptionsByStudentIdUseCase getAllSubscriptionsByStudentIdUseCase;

    @Test
    void createStudentTest() throws Exception {
        // given
        CreateStudentRequest request = new CreateStudentRequest("test name");
        given(createStudentUseCase.createStudent(request.toCommand())).willReturn(new StudentDto(1L, request.name()));

        // when & then
        mvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(request.name()));
        then(createStudentUseCase).should().createStudent(request.toCommand());
    }

    @Test
    void getAllSubscriptionsByStudentIdTest() throws Exception {
        // given
        long studentId = 1L;
        given(getAllSubscriptionsByStudentIdUseCase.getAllSubscriptionsByStudentId(studentId)).willReturn(List.of(
                new SubscriptionDto(1L, 1L, 1L),
                new SubscriptionDto(2L, 1L, 4L),
                new SubscriptionDto(3L, 1L, 6L)
        ));

        // when & then
        mvc.perform(get("/api/v1/students/{studentId}/subscriptions", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].studentId").value(1L))
                .andExpect(jsonPath("$[0].schoolPageId").value(1L))

                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].studentId").value(1L))
                .andExpect(jsonPath("$[1].schoolPageId").value(4L))

                .andExpect(jsonPath("$[2].id").value(3L))
                .andExpect(jsonPath("$[2].studentId").value(1L))
                .andExpect(jsonPath("$[2].schoolPageId").value(6L))
        ;
        then(getAllSubscriptionsByStudentIdUseCase).should().getAllSubscriptionsByStudentId(studentId);
    }
}
