package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.GetNewsPostsByIdsOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import com.example.schoolnewspublishingservice.framework.out.mongo.NewsPostMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetNewsPostsByIdsAdapter implements GetNewsPostsByIdsOutputPort {

    private final NewsPostMongoRepository newsPostMongoRepository;

    @Override
    public List<NewsPost> fetchNewsPostsByIds(List<String> newsPostIds) {
        return newsPostMongoRepository.findByIdIn(newsPostIds);
    }
}
