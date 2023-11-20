package com.example.studentsubscriptionservice.framework.in.web;

import com.example.studentsubscriptionservice.application.in.command.SubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.in.command.UnsubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;
import com.example.studentsubscriptionservice.application.usecase.GetSubscribersUseCase;
import com.example.studentsubscriptionservice.application.usecase.SubscribeSchoolPageUseCase;
import com.example.studentsubscriptionservice.application.usecase.UnsubscribeSchoolPageUseCase;
import com.example.studentsubscriptionservice.framework.in.web.request.SubscribeSchoolPageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SchoolPageApiController.class)
class SchoolPageApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubscribeSchoolPageUseCase subscribeSchoolPageUseCase;

    @MockBean
    private UnsubscribeSchoolPageUseCase unsubscribeSchoolPageUseCase;

    @MockBean
    private GetSubscribersUseCase getSubscribersUseCase;

    @DisplayName("학교 페이지 구독 시 구독 DTO가 정상적으로 반환된다")
    @Test
    void givenStudentIdAndSchoolPageId_whenSubscribing_thenReturnSubscriptionDto() throws Exception {
        // given
        long schoolPageId = 1L;
        SubscribeSchoolPageRequest request = new SubscribeSchoolPageRequest(1L);
        given(subscribeSchoolPageUseCase.subscribeSchoolPage(
                new SubscribeSchoolPageCommand(request.studentId(), schoolPageId)
        )).willReturn(new SubscriptionDto(1L, request.studentId(), schoolPageId));

        // when & then
        mvc.perform(post("/api/v1/school-pages/{schoolPageId}/subscriptions", schoolPageId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated());
    }

    @DisplayName("학교 페이지 구독 취소 시 상태 코드 204를 반환한다")
    @Test
    void givenStudentIdAndSchoolPageId_whenUnsubscribing_thenReturnStatusNoContent() throws Exception {
        // given
        long schoolPageId = 1L;
        long studentId = 1L;
        UnsubscribeSchoolPageCommand command = new UnsubscribeSchoolPageCommand(studentId, schoolPageId);

        // when & then
        mvc.perform(delete("/api/v1/school-pages/{schoolPageId}/subscriptions", schoolPageId)
                .param("studentId", String.valueOf(studentId)))
                .andExpect(status().isNoContent());
        then(unsubscribeSchoolPageUseCase).should().unsubscribeSchoolPage(command);
    }
}
