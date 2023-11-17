package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.SchoolPagePermissionOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Component
public class SchoolPagePermissionAdapter implements SchoolPagePermissionOutputPort {

    @Value("${external.school-page-management-service.host}")
    private String schoolPageManagementServiceHost;

    private final RestTemplate restTemplate;

    @Override
    public boolean checkPermission(long administratorId, long schoolPageId) {
        String uri = UriComponentsBuilder.fromHttpUrl(schoolPageManagementServiceHost)
                .path("api/v1/permissions/check")
                .queryParam("administratorId", administratorId)
                .queryParam("schoolPageId", schoolPageId)
                .build()
                .toUriString();

        ResponseEntity<Boolean> response = restTemplate.getForEntity(uri, Boolean.class);

        return response.getBody() != null
                ? response.getBody()
                : false;
    }
}
