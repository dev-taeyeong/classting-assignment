package com.example.schoolnewspublishingservice.application.in.dto;

public record CreateNewsPostCommand(

        long schoolPageId,
        String title,
        String content
) {
}
