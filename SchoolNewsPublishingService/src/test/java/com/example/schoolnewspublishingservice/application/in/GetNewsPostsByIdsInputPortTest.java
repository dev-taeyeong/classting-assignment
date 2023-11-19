package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.out.GetNewsPostsByIdsOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class GetNewsPostsByIdsInputPortTest {

    @InjectMocks
    private GetNewsPostsByIdsInputPort sut;

    @Mock
    private GetNewsPostsByIdsOutputPort getNewsPostsByIdsOutputPort;

    @DisplayName("특정 ID의 뉴스 포스트들을 조회 시 해당 NewsPostDto 목록이 반환된다")
    @Test
    void givenNewsPostIds_whenRequestingNewsPosts_thenReturnCorrespondingNewsPostDtos() {
        // given
        List<String> newsPostIds = List.of("id1", "id2", "id2");
        given(getNewsPostsByIdsOutputPort.fetchNewsPostsByIds(newsPostIds))
                .willReturn(List.of(
                                NewsPost.createNewsPost(1L, "title1", "content1"),
                                NewsPost.createNewsPost(2L, "title2", "content2"),
                                NewsPost.createNewsPost(3L, "title3", "content3")
                        )
                );

        // when
        List<NewsPostDto> result = sut.getNewsPostsByIds(newsPostIds);

        // then
        assertThat(result.get(0).schoolPageId()).isEqualTo(1L);
        assertThat(result.get(0).title()).isEqualTo("title1");
        assertThat(result.get(0).content()).isEqualTo("content1");

        assertThat(result.get(1).schoolPageId()).isEqualTo(2L);
        assertThat(result.get(1).title()).isEqualTo("title2");
        assertThat(result.get(1).content()).isEqualTo("content2");

        assertThat(result.get(2).schoolPageId()).isEqualTo(3L);
        assertThat(result.get(2).title()).isEqualTo("title3");
        assertThat(result.get(2).content()).isEqualTo("content3");

        then(getNewsPostsByIdsOutputPort).should().fetchNewsPostsByIds(newsPostIds);
    }
}
