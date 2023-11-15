package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;
import com.example.schoolpagemanagementservice.application.usecase.CreateSchoolPageUseCase;
import com.example.schoolpagemanagementservice.application.usecase.GetAllSchoolPagesUseCase;
import com.example.schoolpagemanagementservice.framework.in.web.request.CreateSchoolPageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SchoolPageApiController.class)
class SchoolPageApiControllerTest {

    @MockBean
    private CreateSchoolPageUseCase createSchoolPageUseCase;

    @MockBean
    private GetAllSchoolPagesUseCase getAllSchoolPagesUseCase;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createSchoolPageTest() throws Exception {
        // given
        long schoolPageId = 1L;
        long administratorId = 1L;
        String location = "test location";
        String name = "test name";
        CreateSchoolPageRequest request = new CreateSchoolPageRequest(administratorId, location, name);

        given(createSchoolPageUseCase.createSchoolPage(request.toCommand()))
                .willReturn(new SchoolPageDto(schoolPageId, administratorId, location, name));

        // when & then
        mvc.perform(post("/api/v1/school-pages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(schoolPageId))
                .andExpect(jsonPath("$.administratorId").value(administratorId))
                .andExpect(jsonPath("$.location").value(location))
                .andExpect(jsonPath("$.name").value(name));

        then(createSchoolPageUseCase).should().createSchoolPage(request.toCommand());
    }

    @Test
    void getAllSchoolPagesTest() throws Exception {
        // given
        int page = 0;
        int size = 20;
        String sort = "id";
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
        given(getAllSchoolPagesUseCase.getSchoolPages(pageRequest))
                .willReturn(new PageImpl<>(
                        List.of(
                                new SchoolPageDto(1L, 1L, "test location1", "test name1"),
                                new SchoolPageDto(2L, 2L, "test location2", "test name2"))
                        ));

        // when & then
        mvc.perform(get("/api/v1/school-pages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].administratorId").value(1))
                .andExpect(jsonPath("$.content[0].location").value("test location1"))
                .andExpect(jsonPath("$.content[0].name").value("test name1"))

                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].administratorId").value(2))
                .andExpect(jsonPath("$.content[1].location").value("test location2"))
                .andExpect(jsonPath("$.content[1].name").value("test name2"))
        ;

        then(getAllSchoolPagesUseCase).should().getSchoolPages(pageRequest);
    }
}
