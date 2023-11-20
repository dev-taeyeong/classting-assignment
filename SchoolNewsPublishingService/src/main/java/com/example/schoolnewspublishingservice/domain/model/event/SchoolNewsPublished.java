package com.example.schoolnewspublishingservice.domain.model.event;

public record SchoolNewsPublished(

        String newsPostId,
        Long schoolPageId
) {
}
