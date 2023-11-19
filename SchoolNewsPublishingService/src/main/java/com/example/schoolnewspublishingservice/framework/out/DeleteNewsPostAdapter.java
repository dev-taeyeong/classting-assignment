package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.DeleteNewsPostOutputPort;
import com.example.schoolnewspublishingservice.framework.out.mongo.NewsPostMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteNewsPostAdapter implements DeleteNewsPostOutputPort {

    private final NewsPostMongoRepository newsPostMongoRepository;

    @Override
    public void removeById(String newsPostId) {
        newsPostMongoRepository.deleteById(newsPostId);
    }
}
