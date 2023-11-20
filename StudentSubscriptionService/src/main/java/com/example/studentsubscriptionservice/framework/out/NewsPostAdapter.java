package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.in.dto.NewsPostDto;
import com.example.studentsubscriptionservice.application.out.NewsPostOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class NewsPostAdapter implements NewsPostOutputPort {

    @Value("${external.school-news-publishing-service.host}")
    private String schoolNewsPublishingServiceHost;

    private final RestTemplate restTemplate;

    @Override
    public List<NewsPostDto> getNewsPostDtosBySchoolPageIds(List<Long> schoolPageIds) {
        String uri = UriComponentsBuilder.fromHttpUrl(schoolNewsPublishingServiceHost)
                .path("api/v1/news-posts/by-school-page-ids")
                .queryParam("schoolPageIds", schoolPageIds)
                .build()
                .toUriString();

        ResponseEntity<List<NewsPostDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        if (response.getBody() == null) {
            return Collections.emptyList();
        }

        return response.getBody();
    }
}
