package com.example.schoolnewspublishingservice.framework.in.web;

import com.example.schoolnewspublishingservice.application.in.dto.CreateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.in.dto.UpdateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.usecase.*;
import com.example.schoolnewspublishingservice.framework.in.web.request.CreateNewsPostRequest;
import com.example.schoolnewspublishingservice.framework.in.web.request.UpdateNewsPostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsPostApiController.class)
class NewsPostApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateNewsPostUseCase createNewsPostUseCase;

    @MockBean
    private GetAllNewsPostsUseCase getAllNewsPostsUseCase;

    @MockBean
    private GetNewsPostsByIdsUseCase getNewsPostsByIdsUseCase;

    @MockBean
    private UpdateNewsPostUseCase updateNewsPostUseCase;

    @MockBean
    private DeleteNewsPostUseCase deleteNewsPostUseCase;

    @MockBean
    private GetNewsPostsBySchoolPageIdUseCase getNewsPostsBySchoolPageIdUseCase;

    @DisplayName("뉴스 포스트를 생성한다")
    @Test
    void shouldReturnNewsPostDto_whenCreatingNewsPost() throws Exception {
        // given
        CreateNewsPostRequest request = new CreateNewsPostRequest(1L, 2L, "test title", "test content");
        CreateNewsPostCommand command = request.toCommand();
        String newsPostId = "newsPostId";
        given(createNewsPostUseCase.createNewsPost(command)).willReturn(
                new NewsPostDto(newsPostId, command.schoolPageId(), command.title(), command.content()));

        // when & then
        mvc.perform(post("/api/v1/news-posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(newsPostId))
                .andExpect(jsonPath("$.schoolPageId").value(request.schoolPageId()))
                .andExpect(jsonPath("$.title").value(request.title()))
                .andExpect(jsonPath("$.content").value(request.content()));
        then(createNewsPostUseCase).should().createNewsPost(command);
    }

    @DisplayName("모든 뉴스 포스트를 페이네이션으로 조회하는 기능 검증")
    @Test
    void shouldReturnPagedNewsPosts_whenRequested() throws Exception {
        // given
        PageRequest pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("id")));
        given(getAllNewsPostsUseCase.getAllNewsPost(pageable)).willReturn(
                new PageImpl(
                        List.of(new NewsPostDto("newsPostId", 1L, "test title", "test content")),
                        pageable,
                        1
                ));

        // when & then
        mvc.perform(get("/api/v1/news-posts")
                        .param("page", "0")
                        .param("size", "20")
                        .param("sort", "id,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value("newsPostId"))
                .andExpect(jsonPath("$.content[0].schoolPageId").value(1L))
                .andExpect(jsonPath("$.content[0].title").value("test title"))
                .andExpect(jsonPath("$.content[0].content").value("test content"))
                .andDo(print());

        then(getAllNewsPostsUseCase).should().getAllNewsPost(pageable);
    }

    @DisplayName("특정 ID의 뉴스 포스트들을 정확히 조회하는지 검증")
    @Test
    void givenNewsPostIds_whenRequestingNewsPosts_thenReturnNewsPosts() throws Exception {
        // given
        List<String> newsPostIds = List.of("id1", "id2", "id3");
        given(getNewsPostsByIdsUseCase.getNewsPostsByIds(newsPostIds))
                .willReturn(List.of(
                        new NewsPostDto(newsPostIds.get(0), 1L, "test title1", "test content1"),
                        new NewsPostDto(newsPostIds.get(1), 2L, "test title2", "test content2"),
                        new NewsPostDto(newsPostIds.get(2), 1L, "test title3", "test content3")
                ));

        // when & then
        mvc.perform(get("/api/v1/news-posts/by-ids")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("newsPostIds", String.join(",", newsPostIds)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(newsPostIds.get(0)))
                .andExpect(jsonPath("$[0].schoolPageId").value(1L))
                .andExpect(jsonPath("$[0].title").value("test title1"))
                .andExpect(jsonPath("$[0].content").value("test content1"))

                .andExpect(jsonPath("$[1].id").value(newsPostIds.get(1)))
                .andExpect(jsonPath("$[1].schoolPageId").value(2L))
                .andExpect(jsonPath("$[1].title").value("test title2"))
                .andExpect(jsonPath("$[1].content").value("test content2"))

                .andExpect(jsonPath("$[2].id").value(newsPostIds.get(2)))
                .andExpect(jsonPath("$[2].schoolPageId").value(1L))
                .andExpect(jsonPath("$[2].title").value("test title3"))
                .andExpect(jsonPath("$[2].content").value("test content3"))
                .andDo(print());
    }

    @DisplayName("뉴스 포스트 수정 요청시 제대로 수정되는지 검증")
    @Test
    void shouldUpdateNewsPost_whenUpdateRequestIsMade() throws Exception {
        // given
        UpdateNewsPostRequest request = new UpdateNewsPostRequest(1L, "new title", "new content");
        String newsPostId = "newsPostId";
        long schoolPageId = 1L;
        given(updateNewsPostUseCase.updateNewsPost(
                        new UpdateNewsPostCommand(
                                newsPostId,
                                request.administratorId(),
                                request.newTitle(),
                                request.newContent())
                )
        ).willReturn(new NewsPostDto(newsPostId, schoolPageId, request.newTitle(), request.newContent()));

        // when & then
        mvc.perform(put("/api/v1/news-posts/{newsPostId}", newsPostId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newsPostId))
                .andExpect(jsonPath("$.schoolPageId").value(schoolPageId))
                .andExpect(jsonPath("$.title").value(request.newTitle()))
                .andExpect(jsonPath("$.content").value(request.newContent()))
                .andDo(print());
    }
}
