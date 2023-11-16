package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.UpdateNewsPostOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import com.example.schoolnewspublishingservice.framework.out.mongo.NewsPostMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UpdateNewsPostAdapter implements UpdateNewsPostOutputPort {

    private final NewsPostMongoRepository newsPostMongoRepository;

    @Override
    public Optional<NewsPost> fetchNewsPostById(String id) {
        return newsPostMongoRepository.findById(id);
    }

    @Override
    public NewsPost save(NewsPost newsPost) {
        return newsPostMongoRepository.save(newsPost);
    }
}
