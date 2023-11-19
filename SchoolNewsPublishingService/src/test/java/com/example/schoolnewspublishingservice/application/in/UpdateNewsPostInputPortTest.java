package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.in.dto.UpdateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.out.UpdateNewsPostOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UpdateNewsPostInputPortTest {

    @InjectMocks
    private UpdateNewsPostInputPort sut;

    @Mock
    private UpdateNewsPostOutputPort updateNewsPostOutputPort;

    @DisplayName("뉴스 포스트를 업데이트 하면 존재하는 뉴스 포스트에 대해 NewsPostDto 반환된다")
    @Test
    void givenUpdateNewsPostCommandForExistingNewsPost_whenUpdating_thenReturnUpdatedNewsPostDto() {
        // given
        UpdateNewsPostCommand command = new UpdateNewsPostCommand("id", 1L, "newTitle", "newContent");
        given(updateNewsPostOutputPort.fetchNewsPostById("id"))
                .willReturn(Optional.of(NewsPost.createNewsPost(1L, "title", "content")));

        // when
        NewsPostDto newsPostDto = sut.updateNewsPost(command);

        // then
        assertThat(newsPostDto.schoolPageId()).isEqualTo(1L);
        assertThat(newsPostDto.title()).isEqualTo(command.newTitle());
        assertThat(newsPostDto.content()).isEqualTo(command.newContent());

        then(updateNewsPostOutputPort).should().fetchNewsPostById("id");
        then(updateNewsPostOutputPort).should().save(any(NewsPost.class));
    }
}
