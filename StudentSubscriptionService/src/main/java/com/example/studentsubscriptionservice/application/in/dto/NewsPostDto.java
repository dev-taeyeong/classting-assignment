package com.example.studentsubscriptionservice.application.in.dto;

public record NewsPostDto(

        String id,
        Long schoolPageId,
        String title,
        String content
) {
}
