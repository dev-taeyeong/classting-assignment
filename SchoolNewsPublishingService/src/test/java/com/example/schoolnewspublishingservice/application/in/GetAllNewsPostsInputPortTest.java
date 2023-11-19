package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.out.GetAllNewsPostsOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class GetAllNewsPostsInputPortTest {

    @InjectMocks
    private GetAllNewsPostsInputPort sut;

    @Mock
    private GetAllNewsPostsOutputPort getAllNewsPostsOutputPort;

    @DisplayName("모든 뉴스 포스트를 페이지네이션으로 조회 시 NewsPostDto 목록이 반환된다")
    @Test
    void givenPageableRequest_whenRequestingAllNewsPosts_thenReturnPagedNewsPostDtos() {
        // given
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("id")));
        given(getAllNewsPostsOutputPort.fetchAll(pageable))
                .willReturn(new PageImpl<>(
                        List.of(
                                NewsPost.createNewsPost(1L, "title1", "content1"),
                                NewsPost.createNewsPost(2L, "title2", "content2"),
                                NewsPost.createNewsPost(3L, "title3", "content3")
                        )
                ));

        // when
        Page<NewsPostDto> newsPostDtos = sut.getAllNewsPost(pageable);

        // then
        assertThat(newsPostDtos.getContent().get(0).schoolPageId()).isEqualTo(1L);
        assertThat(newsPostDtos.getContent().get(0).title()).isEqualTo("title1");
        assertThat(newsPostDtos.getContent().get(0).content()).isEqualTo("content1");

        assertThat(newsPostDtos.getContent().get(1).schoolPageId()).isEqualTo(2L);
        assertThat(newsPostDtos.getContent().get(1).title()).isEqualTo("title2");
        assertThat(newsPostDtos.getContent().get(1).content()).isEqualTo("content2");

        assertThat(newsPostDtos.getContent().get(2).schoolPageId()).isEqualTo(3L);
        assertThat(newsPostDtos.getContent().get(2).title()).isEqualTo("title3");
        assertThat(newsPostDtos.getContent().get(2).content()).isEqualTo("content3");

        then(getAllNewsPostsOutputPort).should().fetchAll(pageable);
    }
}
