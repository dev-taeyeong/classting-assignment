package com.example.studentsubscriptionservice.framework.in.web;

import com.example.studentsubscriptionservice.application.in.dto.SchoolPageDto;
import com.example.studentsubscriptionservice.application.in.dto.StudentDto;
import com.example.studentsubscriptionservice.application.usecase.CreateStudentUseCase;
import com.example.studentsubscriptionservice.application.usecase.GetAllStudentsUseCase;
import com.example.studentsubscriptionservice.application.usecase.GetSubscriptionSchoolPagesByStudentIdUseCase;
import com.example.studentsubscriptionservice.application.usecase.IsSubscribedUseCase;
import com.example.studentsubscriptionservice.framework.in.web.request.CreateStudentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
    private GetSubscriptionSchoolPagesByStudentIdUseCase getSubscriptionSchoolPagesByStudentIdUseCase;

    @MockBean
    private IsSubscribedUseCase isSubscribedUseCase;

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
        given(getSubscriptionSchoolPagesByStudentIdUseCase.getAllSubscriptionSchoolPagesByStudentId(studentId)).willReturn(List.of(
                new SchoolPageDto(1L, 1L, "location1", "name1"),
                new SchoolPageDto(2L, 2L, "location2", "name2"),
                new SchoolPageDto(3L, 3L, "location3", "name3")
        ));

        // when & then
        mvc.perform(get("/api/v1/students/{studentId}/subscriptions", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].administratorId").value(1L))
                .andExpect(jsonPath("$[0].location").value("location1"))
                .andExpect(jsonPath("$[0].name").value("name1"))

                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].administratorId").value(2L))
                .andExpect(jsonPath("$[1].location").value("location2"))
                .andExpect(jsonPath("$[1].name").value("name2"))

                .andExpect(jsonPath("$[2].id").value(3L))
                .andExpect(jsonPath("$[2].administratorId").value(3L))
                .andExpect(jsonPath("$[2].location").value("location3"))
                .andExpect(jsonPath("$[2].name").value("name3"))
        ;
        then(getSubscriptionSchoolPagesByStudentIdUseCase).should().getAllSubscriptionSchoolPagesByStudentId(studentId);
    }
}
