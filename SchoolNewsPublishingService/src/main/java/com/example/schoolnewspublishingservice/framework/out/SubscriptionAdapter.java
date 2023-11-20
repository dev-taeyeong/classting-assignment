package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.SubscriptionOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Component
public class SubscriptionAdapter implements SubscriptionOutputPort {

    @Value("${external.student-subscription-service.host}")
    private String studentSubscriptionServiceHost;

    private final RestTemplate restTemplate;

    @Override
    public boolean check(Long schoolPageId, Long studentId) {
        String uri = UriComponentsBuilder.fromHttpUrl(studentSubscriptionServiceHost)
                .path("api/v1/students/" + studentId + "/subscription-status")
                .queryParam("schoolPageId", schoolPageId)
                .build()
                .toUriString();

        ResponseEntity<Boolean> response = restTemplate.getForEntity(uri, Boolean.class);

        if (response.getBody() == null) {
            return false;
        }

        return response.getBody();
    }
}
