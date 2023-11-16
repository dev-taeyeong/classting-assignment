package com.example.schoolnewspublishingservice.framework.in.web.request;

public record UpdateNewsPostRequest(

        Long administratorId,
        String newTitle,
        String newContent
) {
}
