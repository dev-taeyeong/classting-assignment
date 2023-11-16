package com.example.schoolnewspublishingservice.application.out;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;

import java.util.Optional;

public interface UpdateNewsPostOutputPort {

    Optional<NewsPost> fetchNewsPostById(String id);

    NewsPost save(NewsPost newsPost);
}
