package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.dto.CheckPermissionQuery;
import com.example.schoolpagemanagementservice.application.usecase.CheckPermissionUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PermissionApiController.class)
class PermissionApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CheckPermissionUseCase checkPermissionUseCase;

    @DisplayName("특정 학교 페이지와 관리자에 대한 권한 확인이 정상적으로 수행되는지 검증")
    @Test
    void givenSchoolPageIdAndAdministratorId_whenCheckingPermission_thenReturnPermissionStatus() throws Exception {
        // given
        long schoolPageId = 1L;
        long administratorId = 1L;
        CheckPermissionQuery query = new CheckPermissionQuery(schoolPageId, administratorId);
        given(checkPermissionUseCase.checkPermission(query))
                .willReturn(true);

        // when & then
        mvc.perform(get("/api/v1/permissions/check")
                        .param("schoolPageId", String.valueOf(schoolPageId))
                        .param("administratorId", String.valueOf(administratorId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
        then(checkPermissionUseCase).should().checkPermission(query);
    }
}
