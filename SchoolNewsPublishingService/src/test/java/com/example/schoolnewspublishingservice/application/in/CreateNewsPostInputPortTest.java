package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.CreateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.out.CreateNewsPostOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateNewsPostInputPortTest {

    @InjectMocks
    private CreateNewsPostInputPort sut;

    @Mock
    private CreateNewsPostOutputPort createNewsPostOutputPort;

    @DisplayName("뉴스 포스트 생성 시 권한 확인 후 NewsPostDto를 반환한다")
    @Test
    void givenCreateNewsPostCommandWithValidPermission_whenCreatingNewsPost_thenReturnNewsPostDto() {
        // given
        CreateNewsPostCommand command = new CreateNewsPostCommand(1L, 2L, "test title", "test content");
        NewsPost newsPost = NewsPost.createNewsPost(command.schoolPageId(), command.title(), command.content());
        when(createNewsPostOutputPort.save(any(NewsPost.class))).thenReturn(newsPost);

        // when
        NewsPostDto result = sut.createNewsPost(command);

        // then
        assertThat(result.schoolPageId()).isEqualTo(command.schoolPageId());
        assertThat(result.title()).isEqualTo(command.title());
        assertThat(result.content()).isEqualTo(command.content());

        then(createNewsPostOutputPort).should().save(any(NewsPost.class));
    }
}
