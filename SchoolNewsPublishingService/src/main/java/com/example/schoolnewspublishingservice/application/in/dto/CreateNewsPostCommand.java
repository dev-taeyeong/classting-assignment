package com.example.schoolnewspublishingservice.application.in.dto;

public record CreateNewsPostCommand(

        Long administratorId,
        Long schoolPageId,
        String title,
        String content
) {
}
