package com.example.schoolnewspublishingservice.application.in.dto;

public record UpdateNewsPostCommand(

        String newsPostId,
        Long administratorId,
        String newTitle,
        String newContent
) {
}
