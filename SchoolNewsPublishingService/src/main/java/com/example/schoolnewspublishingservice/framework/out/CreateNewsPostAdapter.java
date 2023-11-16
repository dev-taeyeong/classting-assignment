package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.CreateNewsPostOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import com.example.schoolnewspublishingservice.framework.out.mongo.NewsPostMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateNewsPostAdapter implements CreateNewsPostOutputPort {

    private final NewsPostMongoRepository newsPostMongoRepository;

    @Override
    public NewsPost save(NewsPost newsPost) {
        return newsPostMongoRepository.save(newsPost);
    }
}
