package com.example.schoolnewspublishingservice.framework.in.web;

import com.example.schoolnewspublishingservice.application.usecase.CreateNewsPostUseCase;
import com.example.schoolnewspublishingservice.framework.in.web.request.CreateNewsPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/news-posts")
@RestController
public class NewsPostApiController {

    private final CreateNewsPostUseCase createNewsPostUseCase;

    @PostMapping
    public void createNewsPost(@RequestBody CreateNewsPostRequest request) {
        createNewsPostUseCase.createNewsPost(request.toCommand());
    }
}
