package com.example.schoolnewspublishingservice.framework.in.web.request;

import com.example.schoolnewspublishingservice.application.in.dto.CreateNewsPostCommand;

public record CreateNewsPostRequest(

        long schoolPageId,
        String title,
        String content
) {

    public CreateNewsPostCommand toCommand() {
        return new CreateNewsPostCommand(schoolPageId, title, content);
    }
}
