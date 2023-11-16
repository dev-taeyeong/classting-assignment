package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.GetAllNewsPostsOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import com.example.schoolnewspublishingservice.framework.out.mongo.NewsPostMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllNewsPostsAdapter implements GetAllNewsPostsOutputPort {

    private final NewsPostMongoRepository newsPostMongoRepository;

    @Override
    public Page<NewsPost> fetchAll(Pageable pageable) {
        return newsPostMongoRepository.findAll(pageable);
    }
}
