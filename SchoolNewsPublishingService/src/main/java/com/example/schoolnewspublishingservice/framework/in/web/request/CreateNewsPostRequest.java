package com.example.schoolnewspublishingservice.framework.in.web.request;

import com.example.schoolnewspublishingservice.application.in.dto.CreateNewsPostCommand;

public record CreateNewsPostRequest(

        Long administratorId,
        Long schoolPageId,
        String title,
        String content
) {

    public CreateNewsPostCommand toCommand() {
        return new CreateNewsPostCommand(administratorId, schoolPageId, title, content);
    }
}
