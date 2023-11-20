package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.in.dto.SchoolPageDto;
import com.example.studentsubscriptionservice.application.out.SchoolPageOutputPort;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SchoolPageAdapter implements SchoolPageOutputPort {

    @Value("${external.school-page-management-service.host}")
    private String schoolPageManagementServiceHost;

    private final RestTemplate restTemplate;

    public List<SchoolPageDto> getSchoolPageDatasByIds(List<Long> schoolPageIds) {
        String schoolPageIdsString = schoolPageIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String uri = UriComponentsBuilder.fromHttpUrl(schoolPageManagementServiceHost)
                .path("api/v1/school-pages/by-ids")
                .queryParam("schoolPageIds", schoolPageIdsString)
                .build()
                .toUriString();

        ResponseEntity<List<SchoolPageDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<SchoolPageDto>>() {
        });

        if (response.getBody() == null) {
            return Collections.emptyList();
        }

        return response.getBody();
    }
}
