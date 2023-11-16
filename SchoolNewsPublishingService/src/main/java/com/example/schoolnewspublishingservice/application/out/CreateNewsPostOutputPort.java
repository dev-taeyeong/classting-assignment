package com.example.schoolnewspublishingservice.application.out;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;

public interface CreateNewsPostOutputPort {

    NewsPost save(NewsPost newsPost);
}
